package com.wudi.spring.springbootstart.testnetty.msgpack_netty;

import com.wudi.spring.springbootstart.testnetty.seri_netty.UserInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.catalina.User;

import java.util.logging.Logger;

/**
 * @author Dillon Wu
 * @Title: EchoClientHandler
 * @Description: TODO
 * @date 2019/11/30 15:47
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final Logger log = Logger.getLogger(EchoClientHandler.class.getName());

    private int counter;

    private final int sendNumber;

    public EchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    /**
     * 激活通道，向服务端传递
     * @param ctx
     */
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("=========client channelActive===========");
        UserInfo[] userInfos = UserInfo();

        for (UserInfo infoE:userInfos){
            ctx.write(infoE);
            //ctx.writeAndFlush(infoE);
        }
        ctx.flush();
    }

    private UserInfo[] UserInfo(){
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i=0;i<sendNumber;i++){
            userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setUserName("---AAAA---->"+i);
            userInfos[i]=userInfo;
        }
        return userInfos;
    }

    /**
     * 读取服务响应的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("===========EchoClientHandler   channelRead=============");

        System.out.println("time receive server this is:" + msg+"; the counter is :"+(++counter));
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
