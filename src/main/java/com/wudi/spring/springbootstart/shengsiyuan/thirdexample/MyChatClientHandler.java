package com.wudi.spring.springbootstart.shengsiyuan.thirdexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dillon Wu
 * @Title: MyChatClientHandler
 * @Description: TODO
 * @date 2020/3/3 13:58
 */
@Slf4j
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("********MyChatClientHandler*********"+msg);
    }
}
