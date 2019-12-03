package com.wudi.spring.springbootstart.testnetty.seri_netty;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dillon Wu
 * @Title: MsgPack
 * @Description: msgpack编码
 * @date 2019/12/3 9:38
 */
public class MsgPack {
    public static void main(String[] args) throws Exception{
        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");
        MessagePack msgpack = new MessagePack();
        //Serialize
        byte[] raw = msgpack.write(src);
        //Deserialize directly using a template
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1.get(0));
        System.out.println(dst1.get(1));
        System.out.println(dst1.get(2));

    }
}
