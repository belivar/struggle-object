package com.example.yinglishzhi.security.pgp;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.jcajce.*;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * @author LDZ
 * @date 2019-10-14 15:03
 */
@Slf4j
public class PGPExampleUtil {
    static byte[] compressFile(String fileName, int algorithm) throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));
        comData.close();
        return bOut.toByteArray();
    }

    /**
     * Search a secret key ring collection for a secret key corresponding to keyID if it
     * exists.
     *
     * @param pgpSec a secret key ring collection.
     * @param keyID  keyID we want.
     * @param pass   passphrase to decrypt secret key with.
     * @return the private key.
     * @throws PGPException
     * @throws NoSuchProviderException
     */

    static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass) throws PGPException, NoSuchProviderException {

        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
        if (pgpSecKey == null) {
            return null;
        }

        return pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));

    }

    static PGPPublicKey readPublicKey(String fileName) throws IOException, PGPException {

        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));

        PGPPublicKey pubKey = readPublicKey(keyIn);

        keyIn.close();

        return pubKey;

    }

    public static void main(String[] args) throws NoSuchProviderException, IOException, PGPException {
        String path = "/Users/zhiyinglish/security/morgan/";
        String filename = "morgan.txt";
        String out = path + filename + ".asc";
        String in = path + filename;
        String pub = path + "public.asc";
        String pri = path + "private.asc";
        encryptFile(out, in, pub, pri, true, true, "");
    }


    private static void encryptFile(String outputFileName, String inputFileName, String encKeyFileName, String secertFilename, boolean armor, boolean withIntegrityCheck, String password) throws IOException, NoSuchProviderException, PGPException {

        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));

        PGPPublicKey encKey = PGPExampleUtil1.readPublicKey(encKeyFileName);

        PGPSecretKey pgpSecretKey = PGPExampleUtil1.readSecretKey(secertFilename);

        encryptPGPFile(out, inputFileName, encKey, pgpSecretKey, armor, withIntegrityCheck, password.toCharArray());

        out.close();

    }

    public static void encryptPGPFile(OutputStream out, String fileName, PGPPublicKey encKey, PGPSecretKey pgpSec, boolean armor, boolean withIntegrityCheck, char[] pass) throws IOException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        try {

            PGPEncryptedDataGenerator encGen =
                    new PGPEncryptedDataGenerator(
                            new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(
                                    new SecureRandom())
                                    .setProvider("BC"));

            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
            OutputStream encryptedOut = encGen.open(out, new byte[BUFFER_SIZE]);

            PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
            OutputStream compressedData = comData.open(encryptedOut);

            PGPPrivateKey pgpPrivKey = pgpSec.extractPrivateKey(
                    new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
            PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(
                    pgpSec.getPublicKey().getProperty1lgorithm(), PGPUtil.SHA1).setProvider("BC"));
            sGen.init(PGPSignature.BINARY_DOCUMENT, pgpPrivKey);

            Iterator it = pgpSec.getPublicKey().getUserIDs();
            if (it.hasNext()) {
                PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
                spGen.setSignerUserID(false, (String) it.next());
                sGen.setHashedSubpackets(spGen.generate());
            }
            //BCPGOutputStream bOut = new BCPGOutputStream(compressedData);
            sGen.generateOnePassVersion(false).encode(compressedData);

            File file = new File(fileName);
            PGPLiteralDataGenerator lGen = new PGPLiteralDataGenerator();
            OutputStream lOut = lGen.open(compressedData, PGPLiteralData.BINARY, file.getName(), new Date(),
                    new byte[BUFFER_SIZE]);
            FileInputStream fIn = new FileInputStream(file);
            int ch;

            while ((ch = fIn.read()) >= 0) {
                lOut.write(ch);
                sGen.update((byte) ch);
            }

            fIn.close();
            lOut.close();
            lGen.close();
            sGen.generate().encode(compressedData);
            comData.close();
            compressedData.close();

            encryptedOut.close();
            encGen.close();

            if (armor) {
                out.close();
            }
        } catch (PGPException e) {
            log.error("PGPException:" + fileName + e.getMessage());
        } catch (Exception e) {
            log.error("encryptPGPFile Exception:" + fileName + e.getMessage());
        }
    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * suitable for encryption.
     *
     * @param input data stream containing the public key data
     * @return the first public key found.
     * @throws IOException
     * @throws PGPException
     */

    static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException {

        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());

//

// we just loop through the collection till we find a key suitable for encryption, in the real

// world you would probably want to be a bit smarter about this.

//

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

    static PGPSecretKey readSecretKey(String fileName) throws IOException, PGPException {

        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));

        PGPSecretKey secKey = readSecretKey(keyIn);

        keyIn.close();

        return secKey;

    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * suitable for signature generation.
     *
     * @param input stream to read the secret key ring collection from.
     * @return a secret key.
     * @throws IOException  on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */

    static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException {

        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(input), new JcaKeyFingerprintCalculator());
// we just loop through the collection till we find a key suitable for encryption, in the real
// world you would probably want to be a bit smarter about this.
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

}
