package com.wudi.spring.springbootstart.testnetty.seri_netty;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
/**
 * @author Dillon Wu
 * @Title: UserInfoTest
 * @Description: TODO
 * @date 2019/12/2 18:57
 */
public class UserInfoTest {
    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Welcome to netty");
        userInfo.setUserId(100);
        ByteArrayOutputStream bos = new ByteArrayOutputStreamEx();
        ObjectOutputStream os =  new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is:"+b.length);
        bos.close();
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println("The byte array serializable length is:"+userInfo.codeC().length);
    }
}
