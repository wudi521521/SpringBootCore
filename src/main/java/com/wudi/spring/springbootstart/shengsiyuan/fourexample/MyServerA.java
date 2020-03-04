package com.wudi.spring.springbootstart.shengsiyuan.fourexample;

import com.wudi.spring.springbootstart.shengsiyuan.secondexample.MyServerInitializer;
import com.wudi.spring.springbootstart.shengsiyuan.thirdexample.MyChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Dillon Wu
 * @Title: MyServerA
 * @Description: 测试心跳服务器
 * @date 2020/3/3 16:12
 */
public class MyServerA {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //这个handler()对应的是bossGroup,childHandler对应的是workerGroup,添加日志处理
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new MyServerInitializerA());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
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
