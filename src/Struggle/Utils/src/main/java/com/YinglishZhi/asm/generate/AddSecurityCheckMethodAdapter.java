package com.YinglishZhi.asm.generate;


import org.objectweb.asm.*;
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
