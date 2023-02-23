package com.resource.listener;

public class RainListener implements WeatherListener{
    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof RainWeather){
            System.out.println("today having umbrella");
        }

    }
}
