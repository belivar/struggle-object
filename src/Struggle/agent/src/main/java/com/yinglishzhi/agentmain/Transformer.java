package com.yinglishzhi.agentmain;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

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
            log.error("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }

    public byte[] transform(ClassLoader l, String className, Class<?> c, ProtectionDomain pd, byte[] b) {

        log.info("加载了类：{}", className);
        if (!className.equals(replaceClassName)) {
            return null;
        }
        log.info("从文件中加载新的字节码进行替换");
        return getBytesFromFile(classNumberReturns2);

    }
}
