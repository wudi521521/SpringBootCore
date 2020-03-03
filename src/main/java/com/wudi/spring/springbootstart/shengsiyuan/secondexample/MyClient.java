package com.wudi.spring.springbootstart.shengsiyuan.secondexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Dillon Wu
 * @Title: MyClient
 * @Description: 客户端
 * @date 2020/3/2 17:04
 */
public class MyClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new MyClientHandler());
            //绑定端口,绑定端口号,sync()的作用是netty一直等待
            ChannelFuture future = bootstrap.connect("localhost", 8899).sync();
            future.channel().closeFuture().sync();
        }finally {
            //完美关闭
            eventExecutors.shutdownGracefully();
        }
    }
}
