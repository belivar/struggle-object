package com.yinglishzhi.asmtest;

import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.JSRInlinerAdapter;

/**
 * @author LDZ
 * @date 2019-10-28 15:00
 */
public class AdviceWeaver extends ClassVisitor {


    public AdviceWeaver(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }


    @Override
    public MethodVisitor visitMethod(final int access,
                                     final String name,
                                     final String desc,
                                     final String signature,
                                     final String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        if (isIgnore(mv, access, name, desc)) {
            return mv;
        }

        return new CustomAdviceAdapter(Opcodes.ASM5, new JSRInlinerAdapter(mv, access, name, desc, signature, exceptions), access, name, desc);
    }

    @Override
    public void visitEnd() {
        FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer", "J", null, null);
        if (fv != null) {
            fv.visitEnd();
        }
        cv.visitEnd();
    }

    private boolean isIgnore(MethodVisitor mv, int access, String name, String desc) {
        return null == mv
                || !name.equals("getNumber");
    }
}
