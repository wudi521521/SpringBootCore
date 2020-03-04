package com.wudi.spring.springbootstart.shengsiyuan.fiveexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @author Dillon Wu
 * @Title: TextWebSocketFrameHandler
 * @Description: 自定义文本处理器
 * @date 2020/3/4 8:55
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
           log.info("*********TextWebSocketFrameHandler  channelRead0*************"+msg.text());
        System.out.println("接受到信息:"+msg.text()+msg.toString());
           //TODO 发送给客户端,这里Handler处理是个文本，向客户端传递必须是个文本
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间:"+ LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("*****TextWebSocketFrameHandler  handlerAdded******",ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("****TextWebSocketFrameHandler handlerRemoved****",ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("******TextWebSocketFrameHandler exceptionCaught*******",ctx.channel().id().asLongText());
        //异常发生关闭channel
        ctx.channel().close();
    }
}
