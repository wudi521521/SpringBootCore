package com.wudi.spring.springbootstart.shengsiyuan.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Dillon Wu
 * @Title: TestServer
 * @Description:
 * @date 2020/3/1 22:30
 */
public class TestServer {
    public static void main(String[] args) throws Exception{
        //bossGroup不断从客户端接收连接，不会对连接做任何处理，直接把连接交给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup对连接做一些业务处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
     try {
         //服务端启动
         ServerBootstrap serverBootstrap = new ServerBootstrap();
         serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                 childHandler(new TestServerInitializer());

         //绑定端口
         ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
         channelFuture.channel().closeFuture().sync();
     }finally {
         //关闭服务
         bossGroup.shutdownGracefully();
         workerGroup.shutdownGracefully();
     }


    }
}
