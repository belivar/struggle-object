package com.YinglishZhi.asm.generate;


import jdk.internal.org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static sun.tools.java.RuntimeConstants.ACC_PUBLIC;

/**
 * @author LDZ
 * @date 2019-10-28 11:58
 */
public class GenerateClass {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/yinglishzhi/asmtest/MyClass", null, "java/lang/Object", null);

        {
           cw.visitField(ACC_PRIVATE, "name", "Ljava/lang/String;", null, null);
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setName", "(Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 2);
            mv.visitEnd();
        }

        cw.visitEnd();

        return cw.toByteArray();
    }

    public void generateClass() throws Exception {
//        ClassWriter classWriter = new ClassWriter(0);
//        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/yinglishzhi/asmtest/MyClass", null, "java/lang/Object", null);
//
//        ClassAdapter classAdapter = new MyClassAdapter(classWriter);
//        // 定义name属性
//        classAdapter.visitField(ACC_PRIVATE, "name",
//                Type.getDescriptor(String.class), null, null);
//        // 定义构造方法
//        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null,
//                null).visitCode();
//        // 定义setName方法
//        String setMethodDesc = "(" + Type.getDescriptor(String.class) + ")V";
//        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "setName", setMethodDesc,
//                null, null).visitCode();
////        // 定义getName方法
//        String getMethodDesc = "()" + Type.getDescriptor(String.class);
//        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getName", getMethodDesc,
//                null, null).visitCode();

        // 生成字节码

        byte[] classFile = dump();;
        File file = new File("/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/asmtest/MyClass.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(classFile);
        fout.close();
//        // 定义一个类加载器
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
////        Class clazz = classLoader.defineClassFromClassFile(                "com.YinglishZhi.asm.generate.MyClass", classFile);
//        Class clazz = classLoader.loadClass("com.YinglishZhi.asmtest.MyClass");
//        try {
//
//            // 利用反射方式，访问getName
//            Object obj = clazz.newInstance();
//            Method method = clazz.getMethod("getName");
//            System.out.println(obj.toString());
//            System.out.println(method.invoke(obj, null));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhiyinglish/logs/20191028/classes");
        URL[] urls = new URL[]{file.toURI().toURL()};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class<?> clazz = urlClassLoader.loadClass("com.yinglishzhi.asmtest.MyClass");
////        Object instance = clazz.getMethod("getInstance").invoke(null);
////        Method method = clazz.getMethod("getNumber");
////        Object o = method.invoke(instance);
////        System.out.println(o);
//        GenerateClass generateClass = new GenerateClass();
//        generateClass.generateClass();
    }
}
