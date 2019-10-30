package com.yinglishzhi.test;

import com.yinglishzhi.asmtest.AddSecurityCheckClassAdapter;
import jdk.internal.org.objectweb.asm.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * @author LDZ
 * @date 2019-10-15 17:33
 */
public class Transformer implements ClassFileTransformer {

    /**
     * 要改变的类名
     */
    private static final String replaceClassName = "com/yinglishzhi/test/TransClass";

    /**
     * 要改变的类的字节码
     */
    private static final String classNumberReturns2 = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test/TransClass.class";


    public static byte[] addField(String fileName) {
        String out = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test";
        ClassReader classReader = null;
        try {
            classReader = new ClassReader(new FileInputStream(fileName));
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor addField = new AddField(classWriter, "what", Opcodes.ACC_PRIVATE, Type.getDescriptor(String.class), "demo");
            classReader.accept(addField, ClassReader.EXPAND_FRAMES);
            byte[] newClass = classWriter.toByteArray();
            File newFile = new File(out, "NewTransClass.class");
            new FileOutputStream(newFile).write(newClass);
            System.out.println("newFile = " + newFile.getName());
            return newClass;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        addField(classNumberReturns2);
    }

    public static byte[] getBytesFromFile(String fileName) {
        try {
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // 读取文件内容

            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            is.close();
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            return bytes;
        } catch (Exception e) {
            System.err.println("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }


    private void aop(byte[] classByte) {
        System.out.println("===aop===");
        ClassReader cr = new ClassReader(classByte);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();

        File file = new File("/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test/TransClass.class");
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(data);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TransClass transClass = new TransClass();
        int i = transClass.getNumber();
        System.out.println("====" + i);
    }

    @Override
    public byte[] transform(ClassLoader l, String className, Class<?> c, ProtectionDomain pd, byte[] b) {

        if (!className.equals(replaceClassName)) {
            return null;
        }
        ClassReader cr = new ClassReader(b);
        System.out.println("从文件中加载新的字节码进行替换");
        aop(b);
        addField(classNumberReturns2);
        return getBytesFromFile(classNumberReturns2);

    }
}
