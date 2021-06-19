package com.tom.study;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author wangtao
 * @date 2021/5/19
 */
public class WsServerHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final String WS_SERVER = "com.tom.study.WsServer";
    private static final String DECOMPLIE_UTIL = "com.tom.study.DecomplieUtil";
    private static final Logger logger = LoggerFactory.getLogger(WsServerHandler.class);

    private ConcurrentMap<String,Channel> map = new ConcurrentHashMap<>(16);

    private Instrumentation instrumentation;
    private ClassLoader classLoader;



    public WsServerHandler(Instrumentation instrumentation,ClassLoader classLoader){
        this.instrumentation = instrumentation;
        this.classLoader = classLoader;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("连接客户端{}\n",ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        String data = msg.text().trim();
        logger.info("----->,{}",data);
        if (Command.ALL.equalsIgnoreCase(data)) {
            Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
            StringBuilder stringBuilder = new StringBuilder();
            for (Class clazz :allLoadedClasses) {
                stringBuilder.append("类型").append(clazz.getName()).append("\n\r");
            }
            ctx.writeAndFlush(new TextWebSocketFrame(
                stringBuilder.toString()
            ));
        }else if (Command.JVM.equalsIgnoreCase(data)){
            List<String> inputArgs = ManagementFactory
                    .getRuntimeMXBean().getInputArguments();
            StringBuilder sb = new StringBuilder();
            sb.append("参数列表");
            for (String inputArg : inputArgs){
                sb.append(inputArg).append("\t");
            }
            sb.append("\n\r");
            ctx.writeAndFlush(new TextWebSocketFrame(sb.toString()));
        }else if (data.contains(Command.JAD)){
            String[] args = data.split(" ");
            Class<?> aClass = classLoader.loadClass(DECOMPLIE_UTIL);
            if (aClass != null){
                Method method = aClass.getMethod("decompile", String.class,String.class);
                String decomple = method.invoke(null,args[1],"").toString();
                logger.info("decomple--->{}",decomple);
                ctx.writeAndFlush(new TextWebSocketFrame(decomple));
            }
        }else if (Command.QUIT.equalsIgnoreCase(data)){
            Class<?> aClass = classLoader.loadClass(WS_SERVER);
            logger.info("ws server stop--->{}",aClass.getName());
            Object bootstrap = aClass.getMethod(
                    "getInstance", Instrumentation.class, ClassLoader.class).invoke(null,
                    instrumentation, classLoader);
            logger.info("instance--->{}",bootstrap);
            classLoader = null;
            aClass.getMethod("stopServer").invoke(bootstrap);
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        map.put(ctx.name(),ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端断开链接"+channel.id().asLongText());
        map.remove(ctx.name());
    }
}
