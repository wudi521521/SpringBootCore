package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * @author Dillon Wu
 * @Title: NettyMarshallingDecoder
 * @Description: TODO
 * @date 2019/12/13 19:31
 */
public class NettyMarshallingDecoder extends MarshallingDecoder {
    public NettyMarshallingDecoder(UnmarshallerProvider provider) {
        super(provider);
    }

    public NettyMarshallingDecoder(UnmarshallerProvider provider, int maxObjectSize){
        super(provider, maxObjectSize);
    }

    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }
}
