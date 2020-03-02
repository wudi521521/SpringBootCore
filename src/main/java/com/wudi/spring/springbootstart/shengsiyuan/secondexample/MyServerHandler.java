package com.wudi.spring.springbootstart.shengsiyuan.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Dillon Wu
 * @Title: MyServerHandler
 * @Description: 自定义处理器
 * @date 2020/3/2 16:54
 */
@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<String>  {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      log.info("******远程地址********"+ctx.channel().remoteAddress()+","+msg);
      ctx.channel().writeAndFlush("from server"+ UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
