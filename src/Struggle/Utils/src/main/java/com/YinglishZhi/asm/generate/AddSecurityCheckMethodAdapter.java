package com.YinglishZhi.asm.generate;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author LDZ
 * @date 2019-10-28 15:02
 */
public class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5,mv);
    }
    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/YinglishZhi/asm/generate/SecurityChecker",
                "checkSecurity", "()Z");
        super.visitCode();
    }

}
