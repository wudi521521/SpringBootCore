package com.wudi.spring.springbootstart.shengsiyuan.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author Dillon Wu
 * @Title: GrpcServer
 * @Description: TODO
 * @date 2020/3/16 20:04
 */
public class GrpcServer {

    private Server server;

    private void start() throws Exception{
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("server started");

        //关闭JVM
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("关闭JVM");
            GrpcServer.this.stop();
        }));
        System.out.println("执行到这里");

    }
    //关闭服务器
    private void stop(){
        if(null != this.server){
            this.server.shutdown();
        }
    }

    //等待终端,如果3s没有客户端就自动关闭
    private void awaitTermindation() throws Exception{
        if (null !=this.server){
            this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
        }
    }

    public static void main(String[] args) throws Exception{
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        grpcServer.awaitTermindation();
    }
}
