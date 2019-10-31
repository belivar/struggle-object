package com.yinglishzhi.asmtest;

import com.yinglishzhi.test.TransClass;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class GenerateClassTest {

    @Test
    void generateTest() {
        ClassReader cr = null;
        try {
            cr = new ClassReader("com.yinglishzhi.test.TransClass");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        AdviceWeaver classAdapter = new AdviceWeaver(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test/TransClass.class");
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(file);
            fout.write(data);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TransClass transClass = new TransClass();
        int i = transClass.getNumber();
        System.out.println(i);
    }

}
