package com.tom.classparse;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.util.List;

/**
 * @author wangtao
 * @date 2021/5/16
 */
public class Agent {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vm:list){
            System.out.println(vm.id()+"--->"+vm.displayName());
            if ("com.tom.study.agent.AgentStudy".equals(vm.displayName())){
                System.out.println(vm.id()+"--->"+vm.displayName());
                VirtualMachine virtualMachine
                        = getVirtualMachine(vm.id());
                try {
                    virtualMachine.loadAgent("/Users/tom/ideaprojects/javaDataStruct/" +
                                    "myagent/target/my-agent.jar",
                            null);
                } catch (AgentLoadException e) {
                    e.printStackTrace();
                } catch (AgentInitializationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (virtualMachine != null) {
                        try {
                            virtualMachine.detach();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }


    public static VirtualMachine getVirtualMachine(String pid){
        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = VirtualMachine.attach(pid);
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return virtualMachine;
    }
}
