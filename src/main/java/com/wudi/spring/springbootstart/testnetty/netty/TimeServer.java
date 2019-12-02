package com.wudi.spring.springbootstart.testnetty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Dillon Wu
 * @Title: TimeServer
 * @Description: Netty 服务端
 * @date 2019/11/29 22:50
 */
public class TimeServer {

    public void bind(int port) throws Exception{
        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)  //设置线程池
                    .channel(NioServerSocketChannel.class) //设置socket工厂
                    .option(ChannelOption.SO_BACKLOG,1024) //serverSocketChannel的设置，链接缓冲池的大小
                    .childHandler(new ChildChannelHandler());

            //设置参数，TCP参数
           // bootstrap.option(ChannelOption.SO_BACKLOG, 2048);//serverSocketchannel的设置，链接缓冲池的大小
           // bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);//socketchannel的设置,维持链接的活跃，清除死链接
           // bootstrap.childOption(ChannelOption.TCP_NODELAY, true);//socketchannel的设置,关闭延迟发送
            //绑定端口，同步等待成功
            ChannelFuture f=b.bind(port).sync();

            System.out.println("=====TimeServer  START=====");
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
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }


    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){}
        }
        new TimeServer().bind(port);

    }
}
