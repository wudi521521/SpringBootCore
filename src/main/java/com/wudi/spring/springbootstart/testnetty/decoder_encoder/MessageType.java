package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

/**
 * @author Dillon Wu
 * @Title: MessageType
 * @Description: 消息类型
 * @date 2019/12/13 19:48
 */
public enum MessageType {

    //心跳请求，应答
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6),

    //握手请求，应答
    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4);

    byte value;

    MessageType(byte value) {
        this.value = value;
    }
}
