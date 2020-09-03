package com.tom.mycode;

/**
 * @author WangTao
 */
public enum  MessageTypeEnum {

    //普通请求
    MSGTYPE_RESQUEST((byte)1,"普通请求"),
    //普通响应
    MSGTYPE_RESPONSE((byte)2,"普通响应"),

    MSGTYPE_HEARTBEAT_REQUEST((byte)3,"心跳请求"),
    MSGTYPE_HEARTBEAT_RESPONSE((byte)4,"心跳响应");


    private byte type;

    private String name;


    MessageTypeEnum(byte type, String name) {
        this.type = type;
        this.name = name;
    }




}
