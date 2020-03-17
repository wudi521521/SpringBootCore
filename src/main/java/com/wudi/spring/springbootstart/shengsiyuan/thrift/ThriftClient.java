package com.wudi.spring.springbootstart.shengsiyuan.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author Dillon Wu
 * @Title: ThriftClient
 * @Description: Thrif的客户端
 * @date 2020/3/10 21:11
 */
@Slf4j
public class ThriftClient {

    public static void main(String[] args) throws Exception {
        //客户端的协议和服务端的协议要一致
        TTransport tTransport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            //打开socket
            tTransport.open();
            //向服务器发送一个请求
            Person person = client.getPersonByUsername("梧桐树");
            log.info("*************"+person.getUsername()+"-----------"+person.getAge());
            log.info("_____________________");
            Person person2 = new Person();
            person2.setAge(22);
            person2.setMarried(true);
            person2.setUsername("你好武汉");
            client.savePerson(person2);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            tTransport.close();
        }
    }
}
