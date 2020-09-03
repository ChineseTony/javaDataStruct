package com.tom.mycode;

import java.nio.charset.Charset;

/**
 * @author WangTao
 */
public class MyEncodeDecodeConstans {


    /**
     * 魔法数 检验 RpcMessage
     */
    public static final byte[] MAGIC_NUMBER = {(byte)'w',(byte)'t',(byte)'a',(byte)'o'};


    public static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

    //版本信息
    public static final byte VERSION = 1;

    //请求
    public static final byte MSGTYPE_RESQUEST = 1;

    //相应
    public static final byte MSGTYPE_RESPONSE = 2;

    //心跳
    public static final byte MSGTYPE_HEARTBEAT_REQUEST = 3;


    public static final byte MSGTYPE_HEARTBEAT_RESPONSE = 4;


    public static final int HEAD_LENGTH = 16;


    public static final String PING = "ping";

    public static final String PONG = "pong";











}
