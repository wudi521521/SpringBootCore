package com.wudi.spring.springbootstart.shengsiyuan.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author Dillon Wu
 * @Title: GrpcClient
 * @Description: TODO
 * @date 2020/3/16 20:22
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel manageChannel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext().build();

        StudentServiceGrpc.StudentServiceBlockingStub lockingStub = StudentServiceGrpc.newBlockingStub(manageChannel);

        MyResponse myResponse = lockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println("===="+myResponse.getRealname());
    }
}
