package com.tom.http;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.ReferenceCountUtil;

/**
 * @author WangTao
 *
 */
public class HttpServerHandler  extends ChannelInboundHandlerAdapter {


    private static final String FAVICON_ICO = "/favicon.ico";
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof FullHttpRequest){
                FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
                String uri =fullHttpRequest.uri();
                if (uri.equals(FAVICON_ICO)) {
                    return;
                }
                //Get请求
                if (HttpMethod.GET == fullHttpRequest.method()){
                    System.out.println(fullHttpRequest.method().name());
                }
                System.out.println("请求的url地址---->"+uri);
                String s ="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset=\"utf-8\">\n" +
                        "<title>netty http server教程</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>我的第一个标题</h1>\n" +
                        "    <p>我的第一个段落。</p>\n" +
                        "</body>\n" +
                        "</html>";
                FullHttpResponse fullHttpResponse =  FullHttpResponseFactory.buildSuccessResponse(s);
                //处理uri 返回 Response
                boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
                if (!keepAlive) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //保持长连接
                    fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }
}
