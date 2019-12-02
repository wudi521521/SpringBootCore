package com.wudi.spring.springbootstart.testnetty.fix_netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Dillon Wu
 * @Title: EchoServerHandler
 * @Description: 对网络事件进行读写操作
 * @date 2019/11/29 23:06
 */
public class FixServerHandler extends ChannelHandlerAdapter {


    /**
     * 读取服务响应的数据,并且响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("+++++++++  EchoServerHandler channelRead  +++++++++++++");

        String body = (String)msg;
        System.out.println("this is times receive client ["+body+"]");

    }

    /**
     * 将消息发送队列中的消息写入到SocketChannel中发送给对方
     * Netty的write方法并不直接将消息写入SocketChannel中，调用write方法只是把待发送的消息放到
     * 发送缓存数组中，在通过 调用Flush方法，将发送缓存去中的消息全部写到SocketChannel中
     *
     * @param ctx
     * @throws Exception
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 新客户端的接入
     * @param ctx
     * @throws Exception
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("-----timeServerHandler  channelActive-----:"+ctx);
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception{

        System.out.println("------timeServerHandler   channelInactive--------------------");
    }


}
