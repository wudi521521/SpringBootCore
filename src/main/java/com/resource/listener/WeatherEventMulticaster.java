package com.resource.listener;

import org.springframework.stereotype.Component;

@Component
public class WeatherEventMulticaster extends AbstractEventMulticaster{
    @Override
    void doStart() {
        System.out.println("begin broadcast weather");
    }

    @Override
    void doEnd() {
        System.out.println("end broadcast weahter");
    }
}
