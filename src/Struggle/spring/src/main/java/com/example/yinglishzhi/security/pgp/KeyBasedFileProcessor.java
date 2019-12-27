package com.example.yinglishzhi.security.pgp;

import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.operator.jcajce.*;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;

import java.io.*;
import java.math.BigDecimal;
import java.security.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.yinglishzhi.security.pgp.PGPExampleUtil.readSecretKey;

/**
 * @author LDZ
 * @date 2019-10-14 14:59
 */
public class KeyBasedFileProcessor {

    private static void decryptFile(String inputFileName, String keyFileName, char[] passwd, String defaultFileName) throws IOException, NoSuchProviderException {
        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
        decryptFile(in, keyIn, passwd, defaultFileName);
        keyIn.close();
        in.close();
    }

    /**
     * decrypt the passed in message stream
     */

    private static void decryptFile(InputStream in, InputStream keyIn, char[] passwd, String defaultFileName) throws IOException, NoSuchProviderException {
        in = PGPUtil.getDecoderStream(in);
        try {
            JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;

            Object o = pgpF.nextObject();
            // the first object might be a PGP marker packet.
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }

// find the secret key
            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());

            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                sKey = PGPExampleUtil.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }

            if (sKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }
            InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));

            JcaPGPObjectFactory plainFact = new JcaPGPObjectFactory(clear);

            Object message = plainFact.nextObject();

            if (message instanceof PGPCompressedData) {

                PGPCompressedData cData = (PGPCompressedData) message;

                JcaPGPObjectFactory pgpFact = new JcaPGPObjectFactory(cData.getDataStream());

                message = pgpFact.nextObject();

            }

            if (message instanceof PGPLiteralData) {

                PGPLiteralData ld = (PGPLiteralData) message;

                String outFileName = ld.getFileName();

                if (outFileName.length() == 0) {

                    outFileName = defaultFileName;

                } else {

                    outFileName = defaultFileName;

                }

                InputStream unc = ld.getInputStream();

                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));

                Streams.pipeAll(unc, fOut);

                fOut.close();

            } else if (message instanceof PGPOnePassSignatureList) {
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            } else {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }

            if (pbe.isIntegrityProtected()) {

                if (!pbe.verify()) {

                    System.err.println("message failed integrity check");

                } else {

                    System.err.println("message integrity check passed");

                }

            } else {

                System.err.println("no message integrity check");

            }

        } catch (PGPException e) {

            System.err.println(e);

            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }

        }

    }

    private static void encryptFile(String outputFileName, String inputFileName, String encKeyFileName, boolean armor, boolean withIntegrityCheck) throws IOException, NoSuchProviderException, PGPException {

        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));

        PGPPublicKey encKey = PGPExampleUtil.readPublicKey(encKeyFileName);

        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);

        out.close();

    }

    private static void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey, boolean armor, boolean withIntegrityCheck) throws IOException, NoSuchProviderException {

        if (armor) {

            out = new ArmoredOutputStream(out);

        }

        try {

            byte[] bytes = PGPExampleUtil.compressFile(fileName, CompressionAlgorithmTags.ZIP);

            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(

                    new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));

            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));

            OutputStream cOut = encGen.open(out, bytes.length);

            cOut.write(bytes);

            cOut.close();

            if (armor) {

                out.close();

            }

        } catch (PGPException e) {

            System.err.println(e);

            if (e.getUnderlyingException() != null) {

                e.getUnderlyingException().printStackTrace();

            }

        }

    }

    /*
     * create a clear text signed file.
     */
    private static void signFile(String fileName, InputStream keyIn, OutputStream out, char[] pass, String digestName)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, PGPException, SignatureException {
        int digest;
        if (digestName.equals("SHA256")) {
            digest = PGPUtil.SHA256;
        } else if (digestName.equals("SHA384")) {
            digest = PGPUtil.SHA384;
        } else if (digestName.equals("SHA512")) {
            digest = PGPUtil.SHA512;
        } else if (digestName.equals("MD5")) {
            digest = PGPUtil.MD5;
        } else if (digestName.equals("RIPEMD160")) {
            digest = PGPUtil.RIPEMD160;
        } else {
            digest = PGPUtil.SHA1;
        }
        PGPSecretKey pgpSecKey = readSecretKey(keyIn);
        PGPPrivateKey pgpPrivKey = pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pgpSecKey.getPublicKey().getProperty1lgorithm(), digest).setProvider("BC"));
        PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
        sGen.init(PGPSignature.CANONICAL_TEXT_DOCUMENT, pgpPrivKey);

        Iterator it = pgpSecKey.getPublicKey().getUserIDs();
        if (it.hasNext()) {
            spGen.setSignerUserID(false, (String) it.next());
            sGen.setHashedSubpackets(spGen.generate());
        }

        InputStream fIn = new BufferedInputStream(new FileInputStream(fileName));
        ArmoredOutputStream aOut = new ArmoredOutputStream(out);

        aOut.beginClearText(digest);

        //
        // note the last \n/\r/\r\n in the file is ignored
        //
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

    private static int readInputLine(ByteArrayOutputStream bOut, int lookAhead, InputStream fIn)
            throws IOException {
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

    private static void processLine(OutputStream aOut, PGPSignatureGenerator sGen, byte[] line)
            throws SignatureException, IOException {
        // note: trailing white space needs to be removed from the end of
        // each line for signature calculation RFC 4880 Section 7.1
        int length = getLengthWithoutWhiteSpace(line);
        if (length > 0) {
            sGen.update(line, 0, length);
        }

        aOut.write(line, 0, line.length);
    }


    private static void processLine(PGPSignature sig, byte[] line)
            throws SignatureException, IOException {
        int length = getLengthWithoutWhiteSpace(line);
        if (length > 0) {
            sig.update(line, 0, length);
        }
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

    private static int readInputLine(ByteArrayOutputStream bOut, InputStream fIn)
            throws IOException {
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

    private static int readPassedEOL(ByteArrayOutputStream bOut, int lastCh, InputStream fIn)
            throws IOException {
        int lookAhead = fIn.read();

        if (lastCh == '\r' && lookAhead == '\n') {
            bOut.write(lookAhead);
            lookAhead = fIn.read();
        }

        return lookAhead;
    }

    private static byte[] createSignature(String fileName, InputStream keyIn, char[] pass, boolean armor) throws GeneralSecurityException, IOException, PGPException {


        PGPSecretKey pgpSecKey = readSecretKey(keyIn);
        PGPPrivateKey pgpPrivKey = pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider(new BouncyCastleProvider()).build(pass));
        PGPSignatureGenerator sGen = new PGPSignatureGenerator(new JcaPGPContentSignerBuilder(pgpSecKey.getPublicKey().getProperty1lgorithm(), HashAlgorithmTags.SHA1).setProvider(new BouncyCastleProvider()));


        sGen.init(PGPSignature.CANONICAL_TEXT_DOCUMENT, pgpPrivKey);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ArmoredOutputStream aOut = new ArmoredOutputStream(byteOut);
        BCPGOutputStream bOut = new BCPGOutputStream(byteOut);

        InputStream fIn = new BufferedInputStream(new FileInputStream(fileName));

        int ch;
        while ((ch = fIn.read()) >= 0) {
            sGen.update((byte) ch);

        }

        aOut.endClearText();

        fIn.close();

        sGen.generate().encode(bOut);

        if (armor) {
            aOut.close();
        }

        return byteOut.toByteArray();
    }


    /**
     * 生成签名文件
     *
     * @param filePath       签名文件路径
     * @param privateKeyPath 私钥路径
     * @param outFilePath    输出证书路径
     *                       证书名称必须与签名文件名称一样 多后缀: .asc
     *                       比如: 签名文件为:di.ova   那么生成的证书必须为: di.ova.asc
     * @param passWord       证书密码
     * @return 证书字节数组
     */
    public static byte[] signatureCreate(String filePath, String privateKeyPath, String outFilePath, String passWord) {

        try {
            FileInputStream privKeyIn = new FileInputStream(privateKeyPath);
            FileOutputStream signatureOut = new FileOutputStream(outFilePath);
            byte[] sig = createSignature(filePath, privKeyIn, passWord.toCharArray(), true);
            signatureOut.write(sig);
            signatureOut.flush();
            signatureOut.close();
            privKeyIn.close();
            return sig;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void signCl(String filePath, String privateKeyPath, String outFilePath, String passWord) {
        try {
            FileInputStream privKeyIn = new FileInputStream(privateKeyPath);
            FileOutputStream signatureOut = new FileOutputStream(outFilePath);
            signFile(filePath, privKeyIn, signatureOut, passWord.toCharArray(), "SHA256");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//遍历读取文件名

    public static List readFileName() {

        File f = null;

//        DateUtil.getCurrentDate("yyyy-MM-dd");

//        String timerandom = DateUtil.getCurrentDate("yyyy-MM-dd").replace("-", "");

        String path = "";

//        path = "D:" + "/" + timerandom + "/encrypt";

        path = "D:/20190628/encrypt/";

        f = new File(path); //新建文件实例

        File[] list = f.listFiles(); /* 此处获取文件夹下的所有文件 */

        List fileNameList = new ArrayList();

        if (null != list && list.length > 0) {

            for (int i = 0; i < list.length; i++) {

                fileNameList.add(list[i].getName());

                System.out.println("遍历后的文件名：" + fileNameList.get(i));

            }

        } else {


        }

        return fileNameList;

    }


    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        String outFilePath = "/Users/zhiyinglish/security/test/test_en.txt";
        String inFilePath = "/Users/zhiyinglish/security/test/test.txt";
        String publicKeys = "/Users/zhiyinglish/security/public-key.asc";
        try {
            encryptFile(outFilePath, inFilePath, publicKeys, true, true);
        } catch (IOException | NoSuchProviderException | PGPException e) {
            e.printStackTrace();
        }

//        String filePath = "/Users/zhiyinglish/security/test/test.txt";
//        String outFilePath = "/Users/zhiyinglish/security/test/test.txt.asc";
//        String privateKey = "/Users/zhiyinglish/security/private-key.txt";
//        String password = "112233qwerty";
//
//        signCl(filePath, privateKey, outFilePath, password);
    }


    public static void main2(String[] s) throws Exception {
//
//        Security.addProvider(new BouncyCastleProvider());
//
//        boolean encryp = ;  //加密：true  解密：false
//
//        if (encryp) {
//
//            String outPath = "D:\\20190628\\decrypt\\123.DAT";
//
//            String inputPath = "D:\\20190628\\decrypt\\123.txt";
//
//            String publicKeys = "D:\\20190628\\public\\C3B655736E8C77F83183074049F3AB440C1B1830.asc";  //公钥地址
//
//            encryptFile(outPath, inputPath, publicKeys, true, true);

//        } else {

//            String inputPath;
//
//            String outPath;
//
//            String address ="D:/20190628/decrypt/";
//
//            String password ="1234568987";  //私钥的Key
//
//            String privateKeys ="D:\\20190628\\private\\C3B655736E8C77F83183074049F3AB440C1B1830.asc";//私钥地址
//
////批量解密文件
//
//            List fileList =readFileName();
//
//            if (null != fileList) {
//
//                for (int i =0; i < fileList.size(); i++) {
//
//                    inputPath ="D:/20190628/encrypt/" + fileList.get(i);  //被加密的文件
//
//                    if (fileList.get(i).indexOf("DAT") != -1) {
//
//                        outPath = address + fileList.get(i).replace("DAT", "TXT");
//
//                        System.out.println("解密第一个文件，要解密的文件：" + inputPath +"，解密出来的文件" + outPath);
//
//                        decryptFile(inputPath, privateKeys, password.toCharArray(), outPath);
//
//                    }else {
//
//                        continue;
//
//                    }
//
//                }
//
//            }
//
//        }

    }

}
