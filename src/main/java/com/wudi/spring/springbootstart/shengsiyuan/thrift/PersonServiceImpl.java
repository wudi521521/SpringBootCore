package com.wudi.spring.springbootstart.shengsiyuan.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author Dillon Wu
 * @Title: PersonServiceImpl
 * @Description: 实现服务
 * @date 2020/3/10 20:43
 */
@Slf4j
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        log.info("******getPersonByUsername********"+username);
        Person person = new Person();
        person.setAge(1);
        person.setMarried(false);
        person.setUsername("你好北京");
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        log.info("*******savePerson********"+person.getUsername()+"*****"+person.getUsername()+"*******");

    }
}
