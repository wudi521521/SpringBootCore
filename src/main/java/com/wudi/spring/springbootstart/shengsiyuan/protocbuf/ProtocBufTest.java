package com.wudi.spring.springbootstart.shengsiyuan.protocbuf;

/**
 * @author Dillon Wu
 * @Title: ProtocBufTest
 * @Description: 测试类
 * @date 2020/3/9 12:15
 */
public class ProtocBufTest {

    public static void main(String[] args) throws Exception{
        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("张三").setAge(20).setAddress("北京").build();
        //转为字节数组
        byte[] student2ByteArray = student.toByteArray();
        //字节数组转为实体,可以解决跨语言
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        //数字打印
        System.out.println(student2);
        System.out.println(student2.getAddress());
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
    }
}
