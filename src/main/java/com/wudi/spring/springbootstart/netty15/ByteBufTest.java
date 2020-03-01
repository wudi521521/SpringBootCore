package com.wudi.spring.springbootstart.netty15;

import java.nio.ByteBuffer;

/**
 * @author Dillon Wu
 * @Title: ByteBufTest
 * @Description: ByteBuf测试
 * @date 2020/2/4 20:32
 */
public class ByteBufTest {

    public static void main(String[] args) {
        //
        ByteBuffer buffer = ByteBuffer.allocate(10);
        String value = "Netty权威指南";
        int needSize =value.getBytes().length;
        //查看当前文件的编码
        System.out.println("===查看当前文件的编码===:"+System.getProperty("file.encoding"));
        //value.getBytes()如果括号中不写charset,则采用的是System.getProperty("file.encoding")即当前文件的编码方式
        //汉字在b_gbk的长度为2,b_utf8的长度为3，b_iso8859的长度为1
        System.out.println(value.getBytes().length);
        System.out.println("remaining含义:"+buffer.remaining());
        if(buffer.remaining()<needSize){
            int toBeExtSize = needSize>128?needSize:128;
            //capacity是容量
            ByteBuffer tmpBuffer = ByteBuffer.allocate(buffer.capacity()+toBeExtSize);
            buffer.flip();
            tmpBuffer.put(buffer);
            buffer=tmpBuffer;
        }
            System.out.println("扩容完成以后remaining含义:"+buffer.remaining());
            buffer.put(value.getBytes());
            //flip()操作,作用把数据由0->capacity存储范围转为position->limit
            buffer.flip();
            //此时的remaining就是使用容量
            System.out.println("存入数据后remaining的含义:"+buffer.remaining());
            //buffer总体容量
            System.out.println("总体容量capacity:"+buffer.capacity());
            byte[] vArray = new byte[buffer.remaining()];

            System.out.println(":" + vArray.length);

            buffer.get(vArray);
            String decodeValue = new String(vArray);

            System.out.println("decodeValue:" + decodeValue);


    }
}
