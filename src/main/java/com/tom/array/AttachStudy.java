package com.tom.array;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.io.IOException;
import java.util.List;

/**
 * @author wangtao
 * @date 2021/5/20
 */
public class AttachStudy {

    public static void main(String[] args) throws IOException,
            AttachNotSupportedException, AgentLoadException,
            AgentInitializationException {
//        VirtualMachineDescriptor vd = new VirtualMachineDescriptor();
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd:list){
            if (vmd.displayName().equals("com.tom.array.AttachTest")){
                System.out.println(vmd.id()+"---->"+vmd.displayName());
                VirtualMachine attach = VirtualMachine.attach(vmd.id());
                attach.loadAgent("/Users/tom/ideaprojects/javaDataStruct/myagent/target/my-agent.jar");
            }
        }

    }
}
