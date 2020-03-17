package com.wudi.spring.springbootstart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Dillon Wu
 * @Title: Person
 * @Description: 将配置文件中配置的每一属性的值，映射到这个组件中
 * @ConfigurationProperties:告诉Spring Boot将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix =“person”:配置文件中那个下面 所有属性进行----映射
 * @date 2019/10/29 15:37
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;

    private Integer age;

    private Boolean boss;

    private Date birth;

    private Map<String,Object> maps;

    private List<Object> lists;

    public static void main(String[] args) {
        String string = "{\"id\":\"z1.0A1E1A185C9055E7F15E6B3CB43A90CD\",\"pipeline\":\"1381718371.reCodeMp3\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"reqid\":\"ezgAAOdeuqcBzvsV\",\"inputBucket\":\"eblocks-space\",\"inputKey\":\"李志 - 天空之城.mp3\",\"items\":[{\"cmd\":\"avthumb/mp3/ab/128k/ar/44100/acodec/libmp3lame|saveas/ZWJsb2Nrcy1zcGFjZTpjb252ZXJ0X2U3MmMzYmRjLTA5NjctNDJhYi04MjMxLTUyY2Y0OGI3OTI0NS5tcDM=\",\"code\":0,\"desc\":\"The fop was completed successfully\",\"hash\":\"FkNFzrB1Qj3gGPWIDZSz6sApsyMM\",\"key\":\"convert_e72c3bdc-0967-42ab-8231-52cf48b79245.mp3\",\"returnOld\":0}]}";
        System.out.println(!string.contains("callback"));
    }

}
