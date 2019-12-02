package com.wudi.spring.springbootstart.testnetty.echo_netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.tomcat.util.http.fileupload.UploadContext;

import java.util.logging.Logger;

/**
 * @author Dillon Wu
 * @Title: EchoClientHandler
 * @Description: TODO
 * @date 2019/11/30 15:47
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final Logger log = Logger.getLogger(EchoClientHandler.class.getName());

    private int counter;

    private final String ECHO_REQ="hi, WUDI , Welcome to Netty.$_";

    public EchoClientHandler(){
    }

    /**
     * 激活通道，向服务端传递
     * @param ctx
     */
    public void channelActive(ChannelHandlerContext ctx){

        for (int i =0;i<100;i++){

            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    /**
     * 读取服务响应的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("===========EchoClientHandler   channelRead=============");

        System.out.println("time receive server this is:" + msg+"; the counter is :"+(++counter));
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        //释放资源
        log.warning("Unexpected exception from downstream:"+cause.getMessage());
        ctx.close();
    }
}
