package com.wudi.spring.springbootstart.netty13;

import ch.qos.logback.classic.net.SimpleSocketServer;
import com.wudi.spring.springbootstart.testnetty.echo_netty.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;



/**
 * @author Dillon Wu
 * @Title: NettyServer
 * @Description: TODO
 * @date 2020/1/14 14:21
 */
public class NettyServer {
    public static void main(String[] args) throws Exception{
        ServerBootstrap server = new ServerBootstrap();
        //1:绑定两个线程组分别来处理客户端通道的accept和读写时间
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        server.group(parentGroup,childGroup);
        //2:绑定服务端通道的NioServerSocketChannel
        server.channel(NioServerSocketChannel.class);
        //3:给读写时间的线程通道绑定handle去真正处理读写
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new SimpleServerHandler());
                socketChannel.pipeline().addLast(new StringEncoder());
            }
        });
        //4:监听端口
        ChannelFuture future = server.bind(8080).sync();
        future.channel().closeFuture().sync(); //这是个阻塞
    }




    }
