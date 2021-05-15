package com.tom.study;

import cn.hutool.core.thread.NamedThreadFactory;


import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.commons.AdviceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 * @author wangtao
 * @date
 */
@SuppressWarnings("rawtypes")
public class AgentBoot {
    private static final Logger logger = LoggerFactory.getLogger(AgentBoot.class);

    private static final String INIT = "<init>";

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("this is an perform monitor agent.");
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new NamedThreadFactory("jvm-agrs",true));
        executorService.scheduleAtFixedRate(Metric::printJvmAgrs, 0,
                2, TimeUnit.SECONDS);
        inst.addTransformer(new MyClassFileTransformer(),true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class clazz:allLoadedClasses){
            if (clazz.getName().startsWith("java")
                    || clazz.getName().startsWith("sun")){
                continue;
            }
            logger.info("class name:{}",clazz.getName());
        }

    }

    public static void premain(String agentArgs) {

    }

    public static class MyClassFileTransformer implements ClassFileTransformer{
        @Override
        public byte[] transform(ClassLoader loader, String className,
                                Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException {
            // Lcom/tom/classparse/agent/AgentStudy
            String fullName = className.replace("/",".");
            if (!"com.tom.classparse.agent.AgentStudy".equals(fullName)){
                return classfileBuffer;
            }
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new MyClassVisitor(cw);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();

        }
    }


    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor classVisitor) {
            super(Opcodes.ASM5, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor,
                                         String signature, String[] exceptions) {

            //构造函数直接返回
            if (!"test".equals(name)){
                return null;
            }
            MethodVisitor mv = cv.visitMethod(access, name,
                    descriptor, signature, exceptions);

            return new MyMethodVisitor(mv, access, name, descriptor);
        }


    }

//    public static class MyMethodVisitor extends MethodVisitor {
//        public MyMethodVisitor(MethodVisitor mv) {
//            super(Opcodes.ASM5, mv);
//        }
//
//        //此方法在目标方法调用之前调用，所以前置操作可以在这处理
//        @Override
//        public void visitCode() {
//            mv.visitCode();
//            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
//                    "out", "Ljava/io/PrintStream;");
//            mv.visitLdcInsn("method start"+this.mv);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
//                    "println", "(Ljava/lang/String;)V", false);
//        }
//
//        @Override
//        public void visitInsn(int opcode) {
//            if (opcode == Opcodes.IRETURN) {
//                mv.visitFieldInsn(Opcodes.GETSTATIC,
//                        "java/lang/System", "out", "Ljava/io/PrintStream;");
//                mv.visitLdcInsn("method end");
//                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
//                        "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//            }
//            super.visitInsn(opcode);
//        }
//
//
//
//        @Override
//        public void visitMaxs(int maxStack, int maxLocals) {
//            super.visitMaxs(maxStack + 1, maxLocals);
//        }
//    }

    public static class MyMethodVisitor extends AdviceAdapter {
        private String methodName = "";

        protected MyMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
            super(Opcodes.ASM5, mv, access, name, desc);
            this.methodName=name;
        }

        @Override
        public void onMethodEnter() {

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                    "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("method start"+this.methodName);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                    "println", "(Ljava/lang/String;)V", false);
            super.onMethodEnter();
        }


        @Override
        public void onMethodExit(int opcode) {
            super.onMethodExit(opcode);
           mv.visitFieldInsn(Opcodes.GETSTATIC,
                        "java/lang/System", "out", "Ljava/io/PrintStream;");
           mv.visitLdcInsn("method end");
           mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                        "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }


    }


    public static void agentmain(String agentArgs, Instrumentation inst){
        logger.info("this is an agent monitor agent.");
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1
                        ,new NamedThreadFactory("print-jvm",false));
        executorService.scheduleAtFixedRate(() ->{
            Metric.printMemoryInfo();
            Metric.printGCInfo();
        },0, 5000, TimeUnit.MILLISECONDS);
    }
}
