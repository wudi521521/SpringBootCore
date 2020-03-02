package com.wudi.spring.springbootstart.shengsiyuan.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author Dillon Wu
 * @Title: TestHttpServerHandler
 * @Description: 自定义处理器
 * @date 2020/3/1 23:02
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端的请求，并响应客户端的请求
     * messageReceived
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     *  please keep in mind that this mind method will be renamed to messageReceived
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
       if (httpObject instanceof HttpRequest){
           //向客户端返回的数据
           ByteBuf content = Unpooled.copiedBuffer("HelloWorld", CharsetUtil.UTF_8);
           //httpResponse是netty提供的响应客户端的response
           FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                   HttpResponseStatus.OK, content);
           //设置response头信息
           httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
           httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
           //需要调用channelHandlerContext的writeAndFlush()方法，调用write()方法只会放在缓冲区里面，不会发送给客户端
           channelHandlerContext.writeAndFlush(httpResponse);
       }


    }
}
