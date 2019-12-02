package com.wudi.spring.springbootstart.testnetty.echo_netty;

import com.wudi.spring.springbootstart.testnetty.exce_netty.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Dillon Wu
 * @Title: EchoServer
 * @Description: Netty 服务端 (测试 分割符和定长解码器)
 * @date 2019/11/29 22:50
 */
public class EchoServer {

    public void bind(int port) throws Exception{
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)  //设置线程池
                    .channel(NioServerSocketChannel.class) //设置socket工厂
                    .option(ChannelOption.SO_BACKLOG,1024) //serverSocketChannel的设置，链接缓冲池的大小
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });


            ChannelFuture f=b.bind(port).sync();

            System.out.println("=====EchoServer  START=====");
            //等待服务端口监听端口关闭
            f.channel().closeFuture().sync();

        }catch (Exception e){

        }finally {
            System.out.println("#########shutdownGraceFul#############");
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            System.out.println("======================");
            socketChannel.pipeline().addLast(new EchoServerHandler());
        }
    }


    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){}
        }
        new EchoServer().bind(port);

    }
}
