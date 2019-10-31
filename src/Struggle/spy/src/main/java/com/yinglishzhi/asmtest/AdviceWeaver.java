package com.yinglishzhi.asmtest;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.JSRInlinerAdapter;

/**
 * class visitor
 *
 * @author LDZ
 * @date 2019-10-28 15:00
 */
public class AdviceWeaver extends ClassVisitor {


    public static void testMethod(String className) {
        System.out.println("testMethod **** = " + className);
    }


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

//    @Override
//    public void visitEnd() {
//        FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer", "J", null, null);
//        if (fv != null) {
//            fv.visitEnd();
//        }
//        cv.visitEnd();
//    }

    /**
     * 忽略方法
     *
     * @param mv         method visitor
     * @param access     权限
     * @param methodName 方法名称
     * @param descriptor 方法描述符  (see {@link Type}).
     * @return 是否忽略这个方法
     */
    private boolean isIgnore(MethodVisitor mv, int access, String methodName, String descriptor) {
        return null == mv
                || !methodName.equals("getNumber");
    }
}
