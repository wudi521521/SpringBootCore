package com.wudi.spring.springbootstart.shengsiyuan.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author Dillon Wu
 * @Title: MyClientHandler
 * @Description:自定义客户端的处理器
 * @date 2020/3/2 17:20
 */
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("****MyClientHandler 远程地址*****" + ctx.channel().remoteAddress() + "," + msg);
        ctx.writeAndFlush("from client" + LocalDateTime.now());

    }



    /**
     * 连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("*******MyClientHandler   channelActive*******");
       // super.channelActive(ctx);
        ctx.writeAndFlush("来自客户端的问候");

    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
