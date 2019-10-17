package com.yinglishzhi.agenttest;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author LDZ
 * @date 2019-10-16 10:27
 */
public class ViMaTest {
    public static void main(String[] args) throws AttachNotSupportedException, IOException,

            AgentLoadException, AgentInitializationException, InterruptedException {
        // attach to target VM
        VirtualMachine vm = VirtualMachine.attach("17508");
        vm.loadAgent("/Users/zhiyinglish/share/agent.jar");
        Thread.sleep(1000);
        vm.detach();
    }


}
