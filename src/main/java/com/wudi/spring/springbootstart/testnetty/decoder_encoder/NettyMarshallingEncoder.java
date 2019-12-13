package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

/**
 * @author Dillon Wu
 * @Title: NettyMarshallingEncoder
 * @Description: TODO
 * @date 2019/12/13 19:22
 */
public class NettyMarshallingEncoder extends MarshallingEncoder {

    public NettyMarshallingEncoder(MarshallerProvider provider) {
        super(provider);
    }

    public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buf) throws Exception{

        super.encode(ctx,msg,buf);
    }
}
