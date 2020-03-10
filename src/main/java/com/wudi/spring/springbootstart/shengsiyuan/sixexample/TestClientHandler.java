package com.wudi.spring.springbootstart.shengsiyuan.sixexample;

import com.wudi.spring.springbootstart.shengsiyuan.protocbuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Title: TestClientHandler
 * @Description: TODO
 * @date 2020/3/10 10:01
 */
@Slf4j
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        log.info("来自客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("******TestClientHandler********{}" + "出现异常");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("******TestClientHandler********{}" + "客户端连接");
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (0 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().setAge(11).setAddress("北京朝阳").setName("你好北京 person").build()).
                    build();
        } else if (1 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().setDogAge(6).setDogAddress("中华田园犬").setDogName("中华田园犬").build()).
                    build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    //设置枚举
                            setDataType(MyDataInfo.MyMessage.DataType.CatType).
                    //设置实体对象
                            setCat(MyDataInfo.Cat.newBuilder().setCatAge(5).setCatAddress("银渐层小猫咪").setCatName("银渐层小猫咪").build()).
                            build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
