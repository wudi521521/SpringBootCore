package com.resource.listener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEventMulticaster implements EventMulticaster {

    private List<WeatherListener> listenerList = new ArrayList<>();

    @Override
    public void multicastEvent(WeatherEvent event) {
             doStart();
             listenerList.forEach(i->i.onWeatherEvent(event));
             doEnd();
    }

    @Override
    public void addListener(WeatherListener weatherListener) {
               listenerList.add(weatherListener);
    }

    @Override
    public void removeListener(WeatherListener weatherListener) {
               listenerList.remove(weatherListener);
    }

    abstract void doStart();

    abstract void doEnd();
}
