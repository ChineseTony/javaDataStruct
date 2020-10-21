package com.tom.http;

import com.tom.compress.GzipUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.AsciiString;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class FullHttpResponseFactory {

    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONTENT_ENCODING = AsciiString.cached("Content-Encoding");

    private FullHttpResponseFactory(){

    }

    public static FullHttpResponse buildSuccessResponse(String s) {
        byte[] content = s.getBytes();
        byte[] tmp = GzipUtil.compress(content);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,  Unpooled.wrappedBuffer(tmp));
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
        response.headers().set(CONTENT_ENCODING, "gzip");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

    public static FullHttpResponse buildSuccessResponse() {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }
}
