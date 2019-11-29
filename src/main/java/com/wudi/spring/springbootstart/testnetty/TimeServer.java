package com.wudi.spring.springbootstart.testnetty;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Dillon Wu
 * @Title: TimeServer
 * @Description: NIO创建的时间服务器源码
 * @date 2019/11/28 14:49
 */
public class TimeServer {
    public static void main(String[] args) {
        //设置监听端口
        int port=8080;
        if (args !=null && args.length >0){
            try{
              port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        //多路复用类，它是个一个独立线程，负责轮询多路复用器Selector，可以处理多个客户端的并发接入
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
