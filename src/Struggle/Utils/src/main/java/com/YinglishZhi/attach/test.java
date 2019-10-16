package com.YinglishZhi.attach;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author LDZ
 * @date 2019-08-24 11:22
 */
public class test {


    public static void main(String[] args) {
        try {
            VirtualMachine vm = VirtualMachine.attach("1113");
            System.out.println(vm.toString());
        } catch (AttachNotSupportedException | IOException e) {
            e.printStackTrace();
        }
    }
}
