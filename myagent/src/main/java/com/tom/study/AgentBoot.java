package com.tom.study;

import cn.hutool.core.thread.NamedThreadFactory;


import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.commons.AdviceAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
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

    private static final String WS_SERVER = "com.tom.study.WsServer";

    private static final String INIT = "<init>";

    public static void premain(String agentArgs, Instrumentation inst) {
        logger.info("this is an perform monitor agent.");
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader) -> {
            return builder
                    .method(ElementMatchers.any()) // 拦截任意方法
                    .intercept(MethodDelegation.to(MethodCostTime.class)); // 委托
        };
        AgentBuilder.Listener listener = new MyAgentBuilderListener();
        new AgentBuilder.Default()
                // 指定需要拦截的类
                .type(ElementMatchers.nameStartsWith("com.tom.study.agent"))
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }


    private static void printArgs(Instrumentation inst){
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


    public static class MyAgentBuilderListener implements AgentBuilder.Listener{
        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, DynamicType dynamicType) {

        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {

        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, Throwable throwable) {

        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module) {

        }
    }

    public static class MyClassFileTransformer implements ClassFileTransformer{
        @Override
        public byte[] transform(ClassLoader loader, String className,
                                Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException {
            String fullName = className.replace("/",".");
            if (!"com.tom.study.agent.AgentStudy".equals(fullName)){
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
            MethodVisitor mv = cv.visitMethod(access, name,
                    descriptor, signature, exceptions);
            //构造函数直接返回
            if (INIT.equals(name)){
                return mv;
            }
            return new MyMethodVisitor(mv, access, name, descriptor);
        }


    }


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

        try {
            Class aClass = AgentBoot.class.getClassLoader().loadClass(WS_SERVER);
            if (aClass != null){
                Object o = null;
                try {
                    o = aClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (o instanceof WsServer){
                    WsServer w = (WsServer) o;
                    w.setInstrumentation(inst);
                    w.startServer();
                }
            }
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }


    }



    public static void agentmain(String agentArgs){

    }
}
