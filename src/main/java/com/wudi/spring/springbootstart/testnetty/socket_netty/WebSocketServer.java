package com.wudi.spring.springbootstart.testnetty.socket_netty;
import com.wudi.spring.springbootstart.testnetty.echo_netty.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Dillon Wu
 * @Title: WebSocketServer
 * @Description: WebSocket服务端启动WebSocketServer
 * @date 2019/12/9 13:46
 */
public class WebSocketServer {

    public void run(int port) throws Exception{
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
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("http-codec",new HttpServerCodec());
                        pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                        pipeline.addLast("handler",new WebSocketServerHandler());
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

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){}
        }
        new WebSocketServer().run(port);
    }
}
