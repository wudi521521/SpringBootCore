package com.wudi.spring.springbootstart.testnetty.socket_netty;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Dillon Wu
 * @Title: WebSocketServerHandler
 * @Description: TODO
 * @date 2019/12/9 14:07
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    @Autowired
    private WebSocketServerHandler webSocketServerHandler;

    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        //传统的HTTP接入
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx,(FullHttpRequest)msg);

         //WebSocket接入
        }else if(msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx,(WebSocketFrame)msg);
        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception{
        //如果HTTP解码失败，返回HTTP异常

    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame) throws Exception{

    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res){

    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
