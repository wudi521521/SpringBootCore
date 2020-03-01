package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author Dillon Wu
 * @Title: NettyServer
 * @Description: TODO
 * @date 2019/12/13 19:56
 */
public class NettyServer {

    public void bind(int port) throws InterruptedException {

        EventLoopGroup bossGrop = new NioEventLoopGroup();
        EventLoopGroup workerGrop = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGrop, workerGrop).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast(new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        ch.pipeline().addLast(new HeartBeatRespHandler());
                    }
                });
        ChannelFuture future = bootstrap.bind(port).sync();
        System.out.println("server: start ok ! host:" + NettyConstants.REMOTE_IP + ",port:" + NettyConstants.REMOTE_PORT);
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind(NettyConstants.REMOTE_PORT);
    }

}