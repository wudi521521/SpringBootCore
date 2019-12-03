package com.wudi.spring.springbootstart.testnetty.msgpack_netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author Dillon Wu
 * @Title: MsgpackEncoder
 * @Description: msgpack编码器,
 * @date 2019/12/3 9:54
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    /**
     * 负责将Object类型的POJO对象编码为byte数组，然后写入到ByteBuf中
     * @param channelHandlerContext
     * @param o
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack = new MessagePack();
        //Serialize
        byte[] raw =msgpack.write(o);
        byteBuf.writeBytes(raw);
    }
}
