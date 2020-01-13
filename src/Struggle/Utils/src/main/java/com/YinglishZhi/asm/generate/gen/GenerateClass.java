package com.YinglishZhi.asm.generate.gen;


import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author LDZ
 * @date 2019-10-28 11:58
 */
public class GenerateClass {

    public void generateClass() throws IOException, ClassNotFoundException {
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/YinglishZhi/asm/generate/MyClass", null, "java/lang/Object", null);

        ClassAdapter classAdapter = new MyClassAdapter(classWriter);
        // 定义name属性
        classAdapter.visitField(Opcodes.ACC_PRIVATE, "name",
                Type.getDescriptor(String.class), null, null);
        // 定义构造方法
        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null,
                null).visitCode();
        // 定义setName方法
        String setMethodDesc = "(" + Type.getDescriptor(String.class) + ")V";
        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "setName", setMethodDesc,
                null, null).visitCode();
        // 定义getName方法
        String getMethodDesc = "()" + Type.getDescriptor(String.class);
        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getName", getMethodDesc,
                null, null).visitCode();

        // 生成字节码
        byte[] classFile = classWriter.toByteArray();
        File file = new File("/Users/mtdp/dev/SELF/struggle-object/src/Struggle/Utils/target/classes/com/YinglishZhi/asm/generate/MyClass.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(classFile);
        fout.close();

        // 定义一个类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Class clazz = classLoader.defineClassFromClassFile(                "com.YinglishZhi.asm.generate.MyClass", classFile);
        Class clazz = classLoader.loadClass("com.YinglishZhi.asm.generate.MyClass");
        try {

            // 利用反射方式，访问getName
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("getName");
            System.out.println(obj.toString());
            System.out.println(method.invoke(obj, null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GenerateClass generateClass = new GenerateClass();
        generateClass.generateClass();
    }
}
