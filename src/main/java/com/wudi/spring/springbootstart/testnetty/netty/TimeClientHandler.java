package com.wudi.spring.springbootstart.testnetty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * @author Dillon Wu
 * @Title: TimeClientHandler
 * @Description: TODO
 * @date 2019/11/30 15:47
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger log = Logger.getLogger(TimeClientHandler.class.getName());

    private final ByteBuf firstMessage;

    public TimeClientHandler(){
        System.out.println("--------TimeClientHandler-----------");
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    /**
     * 激活通道，向服务端传递
     * @param ctx
     */
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(firstMessage);
    }

    /**
     * 读取服务响应的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("===========TimeClientHandler   channelRead=============");
        ByteBuf buf = (ByteBuf) msg;
        //readableBytes方法可以获取缓冲区可读的字节数
        byte[] req = new byte[buf.readableBytes()];
        //将缓冲区中的字节数组复制到新建的byte数组
        buf.readBytes(req);
        //通过new String构造函数获取请求信息
        String body = new String(req, "UTF-8");
        System.out.println("NOW is:" + body);
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        //释放资源
        log.warning("Unexpected exception from downstream:"+cause.getMessage());
        ctx.close();
    }
}
