package com.resource.listener;

public class SnowListener implements WeatherListener
{
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof SnowWeather){
            System.out.println("today is beautifully");
        }
    }
}
