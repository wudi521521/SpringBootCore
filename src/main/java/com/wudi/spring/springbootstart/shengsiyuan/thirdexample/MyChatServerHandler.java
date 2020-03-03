package com.wudi.spring.springbootstart.shengsiyuan.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dillon Wu
 * @Title: MyChatServerHandler
 * @Description: TODO
 * @date 2020/3/3 13:10
 */
@Slf4j
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 信息接收
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("********MyChatServerHandler channelRead0*************"+msg);
        Channel channel = ctx.channel();
        channelGroup.forEach(item -> {
            //判断发送消息的是否是自己发送的消息
            if (item != channel) {
                item.writeAndFlush(channel.remoteAddress() + "发送消息:" + msg+"\n");
            } else {
                item.writeAndFlush("【自己】" + msg + "\n");
            }
        });
    }

    /**
     * 连接建立 1
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("******MyChatServerHandler  handlerAdded ********");
        Channel channel = ctx.channel();
        //告诉其他的已经连接的客户端，现在又新连接了一个
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "加入\n");
        //所有已经和服务端建立连接的客户端的channel
        channelGroup.add(channel);
    }

    /**
     * 连接断开后操作处理 6
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("******** MyChatServerHandler    handlerRemoved   ******");
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "离开\n");
        //在channelGroup里面去除断开的客户端
        channelGroup.remove(channel);
        //检验channelGroup中的channel的个数
        log.info("*****channelGroup中的判断********"+channelGroup.size());
    }

    /**
     * 连接处于活动状态
     *
     * @param ctx 3
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        log.info("******MyChatServerHandler    channelActive***************" + channel.remoteAddress() + "上线");

    }

    /**
     * 4
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("******MyChatServerHandler    channelInactive***************" + channel.remoteAddress() + "下线");

    }

    /**
     * 2
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("********MyChatServerHandler channelRegistered*************");
    }

    /**
     * 5
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("*************MyChatServerHandler channelUnregistered****************");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        log.info("*******MyChatServerHandler  exceptionCaught**********");
        cause.printStackTrace();
        ctx.close();
    }
}
