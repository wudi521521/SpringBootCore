package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;


import java.util.List;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Title: NettyMessageEncoder
 * @Description: 消息转码
 * @date 2019/12/12 17:37
 */
public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    //MarshallingEncoder marshallingEncoder;
    NettyMarshallingEncoder marshallingEncoder;

    /**
     * 这里和书中不一样，可能有问题
     */
    public NettyMessageEncoder() {
        //this.marshallingEncoder = new MarshallingEncoder(new DefaultMarshallerProvider(new SerialMarshallerFactory(), new MarshallingConfiguration()));
        this.marshallingEncoder = MarshllingCodeFactory.buildMarshallingEncoder();

    }
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("the encode message is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionID());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        sendBuf.writeInt(nettyMessage.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : nettyMessage.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(ctx, value, sendBuf);

        }

        key = null;
        keyArray = null;
        value = null;
        if (nettyMessage.getBody() != null) {
            marshallingEncoder.encode(ctx, nettyMessage.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }
        // 在第4个字节出写入Buffer的长度
        int readableBytes = sendBuf.readableBytes();
        sendBuf.setInt(4, readableBytes);
        // 把Message添加到List传递到下一个Handler
        list.add(sendBuf);
    }
}
