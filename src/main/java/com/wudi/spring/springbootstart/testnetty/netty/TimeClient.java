package com.wudi.spring.springbootstart.testnetty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Dillon Wu
 * @Title: EchoClient
 * @Description: Netty 客户端
 * @date 2019/11/30 15:31
 */
public class TimeClient {



    public void connect(int port,String host) throws Exception{
        //配置客户端NIO线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //发起异步连接操作
            ChannelFuture f = b.connect(host,port).sync();

            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
                //采用默认值
            }
        }
        new TimeClient().connect(port,"127.0.0.1");
    }
}
