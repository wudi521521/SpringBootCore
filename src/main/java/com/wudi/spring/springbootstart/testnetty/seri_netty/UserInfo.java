package com.wudi.spring.springbootstart.testnetty.seri_netty;

import lombok.Data;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author Dillon Wu
 * @Title: UserInfo
 * @Description: TODO
 * @date 2019/12/2 18:52
 */
@Data
public class UserInfo implements Serializable {

    //private static final long serialVersionUID = 1L;

    private String userName;

    private int userId;

    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

}
