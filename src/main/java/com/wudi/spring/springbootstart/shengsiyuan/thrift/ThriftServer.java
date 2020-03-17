package com.wudi.spring.springbootstart.shengsiyuan.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;

/**
 * @author Dillon Wu
 * @Title: ThriftServer
 * @Description: Thrift 服务端
 * @date 2020/3/10 21:01
 */
@Slf4j
public class ThriftServer {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        //THsHa引入了线程池去处理，其模型把读写任务放到线程池去处理。半同步半异步处理
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        //TCompactProtocol协议层
        arg.protocolFactory(new TCompactProtocol.Factory());
        //TFramedTransport 传输层
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        log.info("**************thrift server started");
        //实现死循环
        server.serve();
    }
}
