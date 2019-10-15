package com.example.yinglishzhi.security.pgp;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.jcajce.*;

import java.io.*;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.util.Iterator;

import static org.bouncycastle.bcpg.HashAlgorithmTags.*;

/**
 * morgan 加签 加密
 *
 * @author LDZ
 * @date 2019-10-14 17:01
 */
@Slf4j
public class PgpSecurity {


    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        String filePath = "/Users/zhiyinglish/security/test/test.txt";
        String outFilePath = "/Users/zhiyinglish/security/test/test_en.txt";
        String privateKey = "/Users/zhiyinglish/security/public-key.asc";
        String password = "112233qwerty";
        encryptFile(filePath, privateKey, outFilePath, true, true);
    }

    /**
     * 加签
     *
     * @param filename       文件路径
     * @param privateKeyPath 私钥
     * @param outFilePath    输出文件路径
     * @param password       密码
     * @param digestName     加密方式
     */
    public static void clearSignatureCreate(String filename, String privateKeyPath, String outFilePath, String password, String digestName) {
        try {
            FileInputStream privateKeyIn = new FileInputStream(privateKeyPath);
            FileOutputStream signatureOut = new FileOutputStream(outFilePath);
            clearSignature(filename, privateKeyIn, signatureOut, password.toCharArray(), digestName);
        } catch (FileNotFoundException e) {
            log.error("morgan sign file not found", e);
        } catch (IOException | SignatureException | PGPException e) {
            log.error("morgan sign file error", e);
        }
    }

    /**
     * @param filename           文件路径
     * @param publicKeyPath      公钥
     * @param outFilePath        输出文件路径
     * @param armor
     * @param withIntegrityCheck
     */
    public static void encryptFile(String filename, String publicKeyPath, String outFilePath, boolean armor, boolean withIntegrityCheck) {
        OutputStream out = null;
        InputStream keyIn = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(outFilePath));
            keyIn = new BufferedInputStream(new FileInputStream(publicKeyPath));
            if (armor) {
                out = new ArmoredOutputStream(out);
            }
            byte[] bytes = compressFile(filename, CompressionAlgorithmTags.ZIP);
            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
            PGPPublicKey encKey = readPublicKey(keyIn);
            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
            OutputStream cOut = encGen.open(out, bytes.length);
            cOut.write(bytes);
            cOut.close();
        } catch (PGPException e) {
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        } catch (IOException e) {
            log.error("operate io stream error", e);
        } finally {
            try {
                if (armor && out != null) {
                    out.close();
                }
                if (keyIn != null) {
                    keyIn.close();
                }
            } catch (IOException e) {
                log.error("close stream error", e);
            }
        }
    }

    // ============================================================ private

    /**
     * 加签名
     *
     * @param fileName   文件名
     * @param keyIn      私钥
     * @param out        输出流
     * @param password   密码
     * @param digestName 加密方式
     * @throws IOException        on a problem with using the input stream.
     * @throws PGPException       if there is an issue parsing the input stream.
     * @throws SignatureException signature problem
     */
    private static void clearSignature(String fileName, InputStream keyIn, OutputStream out, char[] password, String digestName) throws IOException, PGPException, SignatureException {
        int digest;
        switch (digestName) {
            case "SHA256":
                digest = SHA256;
                break;
            case "SHA384":
                digest = SHA384;
                break;
            case "SHA512":
                digest = SHA512;
                break;
            case "MD5":
                digest = MD5;
                break;
            case "RIPEMD160":
                digest = RIPEMD160;
                break;
            case "SHA1":
            default:
                digest = SHA1;
                break;
        }

        PGPSecretKey pgpSecKey = readSecretKey(keyIn);
        PGPPrivateKey pgpPrivateKey = pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(password));
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pgpSecKey.getPublicKey().getAlgorithm(), digest).setProvider("BC"));
        PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
        sGen.init(PGPSignature.CANONICAL_TEXT_DOCUMENT, pgpPrivateKey);

        Iterator it = pgpSecKey.getPublicKey().getUserIDs();
        if (it.hasNext()) {
            spGen.setSignerUserID(false, (String) it.next());
            sGen.setHashedSubpackets(spGen.generate());
        }

        InputStream fIn = new BufferedInputStream(new FileInputStream(fileName));
        ArmoredOutputStream aOut = new ArmoredOutputStream(out);
        aOut.beginClearText(digest);

        ByteArrayOutputStream lineOut = new ByteArrayOutputStream();
        int lookAhead = readInputLine(lineOut, fIn);
        processLine(aOut, sGen, lineOut.toByteArray());
        if (lookAhead != -1) {
            do {
                lookAhead = readInputLine(lineOut, lookAhead, fIn);
                sGen.update((byte) '\r');
                sGen.update((byte) '\n');
                processLine(aOut, sGen, lineOut.toByteArray());
            }
            while (lookAhead != -1);
        }
        fIn.close();

        aOut.endClearText();

        BCPGOutputStream bOut = new BCPGOutputStream(aOut);

        sGen.generate().encode(bOut);

        aOut.close();

    }

    private static int readInputLine(ByteArrayOutputStream bOut, int lookAhead, InputStream fIn) throws IOException {
        bOut.reset();
        int ch = lookAhead;
        do {
            bOut.write(ch);
            if (ch == '\r' || ch == '\n') {
                lookAhead = readPassedEOL(bOut, ch, fIn);
                break;
            }
        }
        while ((ch = fIn.read()) >= 0);

        if (ch < 0) {
            lookAhead = -1;
        }

        return lookAhead;
    }

    private static int readInputLine(ByteArrayOutputStream bOut, InputStream fIn) throws IOException {
        bOut.reset();
        int lookAhead = -1;
        int ch;
        while ((ch = fIn.read()) >= 0) {
            bOut.write(ch);
            if (ch == '\r' || ch == '\n') {
                lookAhead = readPassedEOL(bOut, ch, fIn);
                break;
            }
        }
        return lookAhead;
    }

    private static int readPassedEOL(ByteArrayOutputStream bOut, int lastCh, InputStream fIn) throws IOException {
        int lookAhead = fIn.read();
        if (lastCh == '\r' && lookAhead == '\n') {
            bOut.write(lookAhead);
            lookAhead = fIn.read();
        }
        return lookAhead;
    }

    private static void processLine(OutputStream aOut, PGPSignatureGenerator sGen, byte[] line) throws IOException {
        int length = getLengthWithoutWhiteSpace(line);
        if (length > 0) {
            sGen.update(line, 0, length);
        }

        aOut.write(line, 0, line.length);
    }

    private static int getLengthWithoutWhiteSpace(byte[] line) {
        int end = line.length - 1;

        while (end >= 0 && isWhiteSpace(line[end])) {
            end--;
        }

        return end + 1;
    }

    private static boolean isLineEnding(byte b) {
        return b == '\r' || b == '\n';
    }

    private static boolean isWhiteSpace(byte b) {
        return isLineEnding(b) || b == '\t' || b == ' ';
    }

    /**
     * 打开一个密钥环文件并加载第一个适用于签名生成的可用密钥。
     *
     * @param input stream to read the secret key ring collection from.
     * @return a secret key.
     * @throws IOException  on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */

    private static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());
        Iterator keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing) keyRingIter.next();
            Iterator keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext()) {
                PGPSecretKey key = (PGPSecretKey) keyIter.next();
                if (key.isSigningKey()) {
                    return key;
                }
            }
        }
        throw new IllegalArgumentException("Can't find signing key in key ring.");
    }

    /**
     * 打开密钥环文件并加载第一个适用于加密的可用密钥
     *
     * @param input data stream containing the public key data
     * @return the first public key found.
     * @throws IOException  on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */

    private static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());
        Iterator keyRingIter = pgpPub.getKeyRings();
        while (keyRingIter.hasNext()) {
            PGPPublicKeyRing keyRing = (PGPPublicKeyRing) keyRingIter.next();
            Iterator keyIter = keyRing.getPublicKeys();
            while (keyIter.hasNext()) {
                PGPPublicKey key = (PGPPublicKey) keyIter.next();
                if (key.isEncryptionKey()) {
                    return key;
                }
            }
        }
        throw new IllegalArgumentException("Can't find encryption key in key ring.");
    }

    private static byte[] compressFile(String fileName, int algorithm) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));
        comData.close();
        return bOut.toByteArray();
    }
}
