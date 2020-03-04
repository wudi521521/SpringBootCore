package com.wudi.spring.springbootstart.shengsiyuan.fiveexample;

import com.wudi.spring.springbootstart.testnetty.socket_netty.WebSocketServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Dillon Wu
 * @Title: MyServerInitializerB
 * @Description: TODO
 * @date 2020/3/3 20:14
 */
public class MyServerInitializerB extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //块的处理
        pipeline.addLast(new ChunkedWriteHandler());
        //聚合
        pipeline.addLast(new HttpObjectAggregator(8192));
        //ws://server:port/context_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义服务器
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
