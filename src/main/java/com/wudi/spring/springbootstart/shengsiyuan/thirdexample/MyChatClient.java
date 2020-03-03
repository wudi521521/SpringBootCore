package com.wudi.spring.springbootstart.shengsiyuan.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Dillon Wu
 * @Title: MyChatClient
 * @Description: 客户端代码
 * @date 2020/3/3 13:45
 */
public class MyChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyCharClientInitializer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            /*channelFuture.channel().closeFuture().sync();*/
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channel.writeAndFlush(bufferedReader.readLine()+"\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
