package com.wudi.spring.springbootstart.shengsiyuan.sixexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dillon Wu
 * @Title: TestClientHandler
 * @Description: TODO
 * @date 2020/3/10 10:01
 */
@Slf4j
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
     log.info("来自客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("******TestClientHandler********{}"+"出现异常");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("******TestClientHandler********{}"+"客户端连接");
        MyDataInfo.Person.Builder builder = MyDataInfo.Person.newBuilder().
                setAddress("北京地址").setAge(22).setName("中国力量");
        ctx.channel().writeAndFlush(builder);
    }
}
