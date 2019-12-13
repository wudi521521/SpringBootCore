package com.wudi.spring.springbootstart.testnetty.decoder_encoder;


import lombok.Data;

/**
 * @author Dillon Wu
 * @Title: NettyMessage
 * @Description: TODO
 * @date 2019/12/12 17:41
 */
@Data
public final class NettyMessage {

    private Header header;

    private Object body;




}
