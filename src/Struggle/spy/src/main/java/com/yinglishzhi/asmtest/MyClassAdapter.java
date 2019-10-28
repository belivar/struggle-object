package com.yinglishzhi.asmtest;


import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassVisitor;

/**
 * @author LDZ
 * @date 2019-10-28 12:06
 */
public class MyClassAdapter extends ClassAdapter {
    public MyClassAdapter(ClassVisitor cv) {
        super(cv);
    }
}
