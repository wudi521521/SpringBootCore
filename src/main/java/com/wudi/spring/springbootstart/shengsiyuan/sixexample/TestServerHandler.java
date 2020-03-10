package com.wudi.spring.springbootstart.shengsiyuan.sixexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dillon Wu
 * @Title: TestServerHandler
 * @Description: 自定义处理器,指定的泛型的需要和自定义处理器处的泛型指定的是一样的
 * @date 2020/3/10 9:37
 */
@Slf4j
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        log.info("****TestServerHandler打印接受的数据*****"+msg);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("******TestServerHandler******{}"+"服务端连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("*********exceptionCaught*******"+"服务端异常");
    }
}
