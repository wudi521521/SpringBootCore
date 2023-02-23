package com.wudi.spring.springbootstart.test;

import com.resource.listener.*;

public class ListenerTest {
    public static void main(String[] args) {
        WeatherEventMulticaster multicaster = new WeatherEventMulticaster();
        RainListener rainListener = new RainListener();
        SnowListener snowListener = new SnowListener();
        //添加监听的类
        multicaster.addListener(rainListener);
        multicaster.addListener(snowListener);

        //添加事件
        multicaster.multicastEvent(new SnowWeather());
        multicaster.multicastEvent(new RainWeather());

        multicaster.removeListener(rainListener);

        multicaster.multicastEvent(new SnowWeather());
        multicaster.multicastEvent(new RainWeather());


    }
}
