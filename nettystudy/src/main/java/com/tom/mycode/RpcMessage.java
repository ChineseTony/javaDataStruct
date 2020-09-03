package com.tom.mycode;

import java.util.Map;

/**
 * @author WangTao
 */
public class RpcMessage {

    private int requestId;

    private byte messageType;

    private Map<String, String> params;

    private Object data;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "RpcMessage{" +
                "requestId=" + requestId +
                ", messageType=" + messageType +
                ", params=" + params +
                ", data=" + data +
                '}';
    }
}
