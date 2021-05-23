package com.tom.study;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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


    private ConcurrentMap<String,Channel> map = new ConcurrentHashMap<>(16);

    private Instrumentation instrumentation;
    private ClassLoader classLoader;



    public WsServerHandler(Instrumentation instrumentation,ClassLoader classLoader){
        this.instrumentation = instrumentation;
        this.classLoader = classLoader;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        String data = msg.text().trim();
        System.out.println("----->"+data);
        if ("all".equalsIgnoreCase(data)) {
            Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();
            StringBuilder stringBuilder = new StringBuilder();
            for (Class clazz :allLoadedClasses) {
                stringBuilder.append("类型").append(clazz.getName()).append("\n\r");
            }
            ctx.writeAndFlush(new TextWebSocketFrame(
                stringBuilder.toString()
            ));
        }else if ("jvm".equalsIgnoreCase(data)){
            List<String> inputArgs = ManagementFactory
                    .getRuntimeMXBean().getInputArguments();
            StringBuilder sb = new StringBuilder();
            sb.append("参数列表");
            for (String inputArg : inputArgs){
                sb.append(inputArg).append("\t");
            }
            sb.append("\n\r");
            ctx.writeAndFlush(sb.toString());
        }else if (data.contains("jad")){
            String[] args = data.split(" ");
            Class<?> aClass = classLoader.loadClass("com.tom.study.DecomplieUtil");
            if (aClass != null){
                Method method = aClass.getMethod("decompile", String.class,String.class);
                System.out.println(method.invoke(null,args[1],""));
            }
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
