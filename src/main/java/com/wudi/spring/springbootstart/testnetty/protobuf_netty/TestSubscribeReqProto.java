package com.wudi.spring.springbootstart.testnetty.protobuf_netty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dillon Wu
 * @Title: TestSubscribeReqProto
 * @Description: protobuf编码
 * @date 2019/12/5 11:13
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req){

        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws Exception{
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("Dillon wu");
        builder.setProductName("Netty book");
        builder.setAddress("山东");
        List<String> address = new ArrayList<String>();
        address.add("NanJing yuhuatai");
        address.add("BeiJing gugong");

        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode:"+req.toString());
        SubscribeReqProto.SubscribeReq req1 = decode(encode(req));
        System.out.println("after decode:"+req.toString());
        System.out.println("Assert equal:----->"+req1.equals(req));
    }
}
