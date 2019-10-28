package com.YinglishZhi.asm.generate;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author LDZ
 * @date 2019-10-28 15:03
 */
public class Generator {
    private static AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();
    public static void main(String args[]) throws Exception {
        ClassReader cr = new ClassReader("com.YinglishZhi.asm.generate.Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();
        //File file = new File("C:\\Users\\xiaotian\\Desktop\\jvm原理\\Account.class");
        File file = new File("/Users/zhiyinglish/dev/struggle-object/src/Struggle/Utils/target/classes/com/YinglishZhi/asm/generate/Account.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();

//        Class account = classLoader.defineClassFromClassFile("com.market.search.test.Account", data);
//        Account acc =(Account) account.newInstance();
//        acc.operation();
        Account account = new Account();
        account.operation();


    }
    private static class AccountGeneratorClassLoader extends ClassLoader {
        public Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0,classFile.length);
        }
    }
}
