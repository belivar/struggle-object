package com.yinglishzhi.agentmain;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.yinglishzhi.agenttest.VMTest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 注入线程
 *
 * @author LDZ
 * @date 2019-10-15 17:35
 */
@Slf4j
public class AttachThread extends Thread {
    private static final String JAR_PATH = "/Users/zhiyinglish/dev/struggle-object/src/Struggle/agent/target/agent-1.0-SNAPSHOT.jar";

    private static final Class TEST_CLASS = TestMainJar.class;

    private final List<VirtualMachineDescriptor> listBefore;

    private final String jar;

    private AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
        // 记录程序启动时的 VM 集合
        listBefore = vms;
        jar = attachJar;
    }

    public static void main(String[] args) {
        log.info(System.getProperty("user.dir"));
        List<VirtualMachineDescriptor> vmList = VirtualMachine.list();
        log.info("=========当前已经启动的JVM=========");
        for (VirtualMachineDescriptor virtualMachineDescriptor : vmList) {
            log.info("VM id = {}, name = {}", virtualMachineDescriptor.id(), virtualMachineDescriptor.displayName());
        }
        new AttachThread(JAR_PATH, vmList).start();
    }

    public void run() {
        VirtualMachine vm = null;
        List<VirtualMachineDescriptor> listAfter;
        try {
            int count = 0;
            while (true) {
                listAfter = VirtualMachine.list();
                log.info("寻找新启动的JVM......");
                for (VirtualMachineDescriptor vmd : listAfter) {

                    if (!listBefore.contains(vmd)) {
                        // 如果 VM 有增加，我们就认为是被监控的 VM 启动了
                        // 这时，我们开始监控这个 VM
                        log.info("找到新启动的虚拟机 id = {}, name = {}, 附加到该虚拟机", vmd.id(), vmd.displayName());
                        if (vmd.displayName().contains(TEST_CLASS.getSimpleName())) {
                            vm = VirtualMachine.attach(vmd);
                            vm.loadAgent(jar, "666");
                            vm.detach();
                            log.info("从该虚拟机中分离");
                            break;
                        }
                    }
                }
                Thread.sleep(500);
                count++;
                if (null != vm || count >= 100) {
                    break;
                }
            }
            log.info("退出");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
