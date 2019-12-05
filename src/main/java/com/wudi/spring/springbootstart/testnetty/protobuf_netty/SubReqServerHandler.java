package com.wudi.spring.springbootstart.testnetty.protobuf_netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Dillon Wu
 * @Title: SubReqServerHandler
 * @Description: TODO
 * @date 2019/12/5 15:19
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    /**
     * 读取服务响应的数据,并且响应
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("+++++++++  buf channelRead  +++++++++++++");
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
        if ("hello world".equalsIgnoreCase(req.getUserName())){
            System.out.println("+++++++++++++++"+req.toString());
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }

        //ctx.write("已经收到信息".getBytes());
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID){
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setDesc("Netty book order succeed");
        builder.setRespCoder("0");
        System.out.println("{}{}{}{}{}{}{");
        return  builder.build();
    }

    /**
     * 将消息发送队列中的消息写入到SocketChannel中发送给对方
     * Netty的write方法并不直接将消息写入SocketChannel中，调用write方法只是把待发送的消息放到
     * 发送缓存数组中，在通过 调用Flush方法，将发送缓存去中的消息全部写到SocketChannel中
     *
     * @param ctx
     * @throws Exception
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        System.out.println("============channelReadComplete=============");
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("+++buf exceptionCaught++++"+cause);

        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 新客户端的接入
     * @param ctx
     * @throws Exception
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("-----buf  channelActive-----:"+ctx);
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception{

        System.out.println("----buf   channelInactive--------------------");
    }


}
