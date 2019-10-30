package com.yinglishzhi.test;

import jdk.internal.org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

/**
 * @author LDZ
 * @date 2019-10-23 17:40
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
    public void visitEnd() {
        if (!duplicate) {
            FieldVisitor fv = super.visitField(access, name, desc, null, value);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    private static final String classNumberReturns2 = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test/TransClass.class";
    private static final String ou = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/spy/target/classes/com/yinglishzhi/test/";

    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader(new FileInputStream(classNumberReturns2));
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor addField = new AddField(classWriter,
                "field",
                Opcodes.ACC_PRIVATE,
                Type.getDescriptor(String.class),
                "demo"
        );
        classReader.accept(addField, ClassReader.EXPAND_FRAMES);
        byte[] newClass = classWriter.toByteArray();
        File newFile = new File(ou, "NewDemo.class");
        Class clazz = Class.forName("com.yinglishzhi.test.TransClass");
        Method getNumber = clazz.getMethod("getNumber");
        System.out.println(getNumber.invoke(new TransClass()));
        new FileOutputStream(newFile).write(newClass);
    }

}
