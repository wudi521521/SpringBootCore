package com.wudi.spring.springbootstart.testnetty.aio;

/**
 * @author Dillon Wu
 * @Title: TimeServer
 * @Description: TODO
 * @date 2019/11/29 16:09
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args !=null && args.length >0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
                //采用默认值
            }
        }

    }
}
