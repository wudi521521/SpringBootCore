package com.wudi.spring.springbootstart.shengsiyuan.fourexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Title: MyServerInitializerA
 * @Description: TODO
 * @date 2020/3/3 16:27
 */
public class MyServerInitializerA extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //netty自带自定义处理器，读超时是5s,写是7s,读写时10s
        pipeline.addLast(new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandlerA());
    }
}
