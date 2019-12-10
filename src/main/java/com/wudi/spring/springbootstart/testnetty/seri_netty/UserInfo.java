package com.wudi.spring.springbootstart.testnetty.seri_netty;

import lombok.Data;
import org.msgpack.annotation.Message;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author Dillon Wu
 * @Title: UserInfo
 * @Description: TODO
 * @date 2019/12/2 18:52
 */
@Data
@Message
public class UserInfo{

    public UserInfo(){}

    public UserInfo(String userName,int userId){
        this.userId=userId;
        this.userName = userName;
    }

    @NotBlank( message = "请输入名字字段不要为空")
    private String userName;

    @Min(value = 18,message = "请输入大于18岁")
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
