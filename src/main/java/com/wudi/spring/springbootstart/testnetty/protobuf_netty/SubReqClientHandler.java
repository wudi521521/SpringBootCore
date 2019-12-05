package com.wudi.spring.springbootstart.testnetty.protobuf_netty;

import com.wudi.spring.springbootstart.testnetty.msgpack_netty.EchoClientHandler;
import com.wudi.spring.springbootstart.testnetty.seri_netty.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @author Dillon Wu
 * @Title: SubReqClientHandler
 * @Description: TODO
 * @date 2019/12/5 15:33
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    private static final Logger log = Logger.getLogger(EchoClientHandler.class.getName());

    public SubReqClientHandler(){}

    /**
     * 激活通道，向服务端传递
     * @param ctx
     */
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("=========client channelActive===========");


        for (int i=0; i<10;i++){
            ctx.write(subscribeReq(i));
            //ctx.writeAndFlush(infoE);
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subscribeReq(int i){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setAddress("北京");
        builder.setSubReqID(i);
        builder.setUserName("hello world");
        builder.setProductName("商品名");

        return builder.build();
    }


    /**
     * 读取服务响应的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("===========EchoClientHandler   channelRead=============");

        System.out.println("time receive server this is:" + msg+"; the counter is :");
        //如果在ctx.write(mdg)获取到的信息有去服务器了
        //ctx.write(msg);
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        //释放资源
        log.warning("Unexpected exception from downstream:"+cause.getMessage());
        ctx.close();
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        System.out.println("============channelReadComplete=============");
        ctx.flush();
    }
}
