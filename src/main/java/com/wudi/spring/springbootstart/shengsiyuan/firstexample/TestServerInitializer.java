package com.wudi.spring.springbootstart.shengsiyuan.firstexample;

import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Dillon Wu
 * @Title: TestServerInitializer
 * @Description: 初始化通道
 * @date 2020/3/1 22:45
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 连接一旦被创建，就会被注册，连接initChannel此方法
     * @param socketChannel
     * @throws Exception
     *
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        pipeline.addLast("TestHttpServerHandler",new TestHttpServerHandler());
    }
}
