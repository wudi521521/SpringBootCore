package com.wudi.spring.springbootstart.netty14;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Dillon Wu
 * @Title: ClientNetty
 * @Description: TODO
 * @date 2020/1/16 13:55
 */
public class ClientNetty {
    public ClientNetty connect(int port,String host,final String nickName){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                        }
                    });
            //发起同步连接操作
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //关闭，释放线程资源
            group.shutdownGracefully();
        }
        return this;
    }
    public static void main(String[] args) {
        new ClientNetty().connect(8080, "localhost","wei");
    }
}
