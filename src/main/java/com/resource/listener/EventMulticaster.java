package com.resource.listener;

public interface EventMulticaster {

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(WeatherEvent event);

    /**
     * 添加监听器
     * @param weatherListener
     */
    void addListener(WeatherListener weatherListener);

    /**
     * 删除监听器
     * @param weatherListener
     */
    void removeListener(WeatherListener weatherListener);
}
