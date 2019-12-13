package com.wudi.spring.springbootstart.testnetty.decoder_encoder;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Title: Header
 * @Description: final 修饰一个对象，那么这个对象的引用不能变,但是值是不可以变的
 * @date 2019/12/12 17:44
 */
@Data
public final class Header {

    private int crcCode= 0xabef0101;

    //消息长度
    private int length;

    //会话id
    private long sessionID;

    //消息类型
    private byte type;

    //消息优先级
    private byte priority;

    //附件
    private Map<String,Object> attachment=new HashMap<String,Object>();
}
