package com.YinglishZhi.asm;

import jdk.internal.org.objectweb.asm.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * @author LDZ
 * @date 2019-10-23 11:21
 */
public class AddField extends ClassVisitor {

    private String name;
    private int access;
    private String desc;
    private Object value;

    private boolean duplicate;

    public AddField(ClassVisitor cv, String name, int access, String desc, Object value) {
        super(ASM5, cv);
        this.name = name;
        this.access = access;
        this.desc = desc;
        this.value = value;
    }

    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        if (name.equals(this.name)) {
            duplicate = true;
        }
        return super.visitField(i, s, s1, s2, o);
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s + "_tmp", s1, s2, strings);
//        {
//            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//            mv.visitCode();
//            mv.visitVarInsn(ALOAD, 0);
//            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
//            mv.visitInsn(RETURN);
//            mv.visitMaxs(1, 1);
//            mv.visitEnd();
//        }
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        return super.visitMethod(i, s, s1, s2, strings);
    }

    private String setof(String typeof) {
        return "(" + typeof + ")V";
    }

    private String getof(String typeof) {
        return "()" + typeof;
    }

    private int[] loadAndReturnOf(String typeof) {
        if (typeof.equals("I") || typeof.equals("Z")) {
            return new int[]{ILOAD, IRETURN};
        } else if (typeof.equals("J")) {
            return new int[]{LLOAD, LRETURN};
        } else if (typeof.equals("D")) {
            return new int[]{DLOAD, DRETURN};
        } else if (typeof.equals("F")) {
            return new int[]{FLOAD, FRETURN};
        } else {
            return new int[]{ALOAD, ARETURN};
        }
    }

    private void addMethod(ClassWriter cw, MethodVisitor mv, String className,
                           String fieldName) {

    }

    @Override
    public void visitEnd() {
        if (!duplicate) {
            FieldVisitor fv = super.visitField(access, name, desc, null, value);

            String setMethodName = "set" + StringUtils.capitalize(name);
            String getMethodName = "get" + StringUtils.capitalize(name);

            String typeof = Type.getType(String.class).getDescriptor();
            String getof = getof(typeof);
            String setof = setof(typeof);
            int[] loadAndReturnOf = loadAndReturnOf(typeof);

            // getMethod
//            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, getMethodName, getof, null, null);
//            mv.visitCode();
//            mv.visitVarInsn(ALOAD, 0);
//            mv.visitFieldInsn(GETFIELD, "Demo_tmp", name, typeof);
//            mv.visitInsn(loadAndReturnOf[1]);
//            mv.visitMaxs(2, 1);
//            mv.visitEnd();


            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, getMethodName, getof, null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/YinglishZhi/asm/Demo_tmp", name, typeof);
            mv.visitInsn(ARETURN);
//            mv.visitLocalVariable("this", "Lcom/YinglishZhi/asm/Demo_tmp;", null, null, null, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            // setMethod
            mv = super.visitMethod(ACC_PUBLIC, setMethodName, setof, null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(loadAndReturnOf[0], 1);
            mv.visitFieldInsn(PUTFIELD, "com/YinglishZhi/asm/Demo_tmp", name, typeof);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
            if (fv != null) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException {

        String output = "/Users/mtdp/dev/SELF/struggle-object/src/Struggle/Utils/target/classes/com/YinglishZhi/asm";

        ClassReader classReader = new ClassReader(Demo.class.getName());
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor addField = new AddField(classWriter, "field", Opcodes.ACC_PRIVATE, Type.getDescriptor(String.class), "demo");


        classReader.accept(addField, ClassReader.EXPAND_FRAMES);
        byte[] newClass = classWriter.toByteArray();

        File newFile = new File(output, "Demo_tmp.class");

        new FileOutputStream(newFile).write(newClass);



        // 定义一个类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


        Class clazz = null;
        try {
            clazz = classLoader.loadClass("com.YinglishZhi.asm.Demo_tmp");
            // 利用反射方式，访问getName
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("getField");
            System.out.println(obj.toString());
            System.out.println(method.invoke(obj, null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
