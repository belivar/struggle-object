package com.YinglishZhi.asm;

import jdk.internal.org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

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
    public void visitEnd() {
        if (!duplicate) {
            FieldVisitor fv = super.visitField(access, name, desc, null, value);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException {
        String classDir = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/Utils/target/classes/com/YinglishZhi/asm/Demo.class";
        String output = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/Utils/src/main/java/com/YinglishZhi/asm";

        ClassReader classReader = new ClassReader(new FileInputStream(classDir));
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor addField = new AddField(classWriter, "field", Opcodes.ACC_PRIVATE, Type.getDescriptor(String.class), "demo");
        classReader.accept(addField, ClassReader.EXPAND_FRAMES);
        byte[] newClass = classWriter.toByteArray();
        File newFile = new File(output, "NewDemo.class");
        new FileOutputStream(newFile).write(newClass);
    }

}
