package com.yinglishzhi.asm;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;

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
}
