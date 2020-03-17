package com.wudi.spring.springbootstart.shengsiyuan.grpc;

import io.grpc.stub.StreamObserver;

/**
 * @author Dillon Wu
 * @Title: StudentServiceImpl
 * @Description: grpc实现一个内部类
 * @date 2020/3/16 19:47
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受到客户端信息:"+request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }
}
