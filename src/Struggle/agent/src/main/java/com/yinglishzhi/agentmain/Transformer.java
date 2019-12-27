package com.yinglishzhi.agentmain;

import com.yinglishzhi.asm.AddField;
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
@Slf4j
public class Transformer implements ClassFileTransformer {

    /**
     * 要改变的类名
     */
    private static final String replaceClassName = "com/yinglishzhi/agentmain/TransClass";

    /**
     * 要改变的类的字节码
     */
    private static final String classNumberReturns2 = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/agent/target/classes/com/yinglishzhi/agentmain/TransClass.class";


    public static byte[] addField(String fileName) {
        String out = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/agent/target/classes/com/yinglishzhi/agentmain";
        ClassReader classReader = null;
        try {
            classReader = new ClassReader(new FileInputStream(fileName));
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor addField = new AddField(classWriter, "field", Opcodes.ACC_PRIVATE, Type.getDescriptor(String.class), "demo");
            classReader.accept(addField, ClassReader.EXPAND_FRAMES);
            byte[] newClass = classWriter.toByteArray();
            File newFile = new File(out, "NewTransClass.class");
            new FileOutputStream(newFile).write(newClass);
            log.info("newFile = {}", newFile.getName());
            return newClass;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getProperty2ytesFromFile(String fileName) {
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
            log.error("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }

    @Override
    public byte[] transform(ClassLoader l, String className, Class<?> c, ProtectionDomain pd, byte[] b) {

        log.info("加载了类加载器 = {}, 类 = {}, CLASS = {}, ProtectionDomain = {}, byte = {}", l.toString(), className, c.getName(), pd.toString(), Arrays.toString(b));
        if (!className.equals(replaceClassName)) {
            return null;
        }
        ClassReader cr = new ClassReader(b);
        log.info("读了byte className = {}, access = {}, item count = {}", cr.getClassName(), cr.getProperty1ccess(), cr.getItemCount());
        log.info("从文件中加载新的字节码进行替换");
        addField(classNumberReturns2);
        return getProperty2ytesFromFile(classNumberReturns2);

    }
}
