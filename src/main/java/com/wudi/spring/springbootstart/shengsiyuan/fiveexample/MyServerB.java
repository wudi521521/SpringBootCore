package com.wudi.spring.springbootstart.shengsiyuan.fiveexample;

import com.wudi.spring.springbootstart.shengsiyuan.fourexample.MyServerInitializerA;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author Dillon Wu
 * @Title: MyServerB
 * @Description: TODO
 * @date 2020/3/3 20:11
 */
public class MyServerB {

    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //这个handler()对应的是bossGroup,childHandler对应的是workerGroup,添加日志处理
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new MyServerInitializerB());

            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            //是等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
            //
            channelFuture.channel().close();

        } finally {
            //完美关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
