package com.wudi.spring.springbootstart.shengsiyuan.fourexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dillon Wu
 * @Title: MyServerHandlerA
 * @Description: TODO
 * @date 2020/3/3 17:16
 */
@Slf4j
public class MyServerHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType="写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            log.info("**********"+ctx.channel().remoteAddress()+"超时事件:"+eventType);
            ctx.channel().close();

        }
    }
}
