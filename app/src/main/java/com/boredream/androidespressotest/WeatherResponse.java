package com.boredream.androidespressotest;

/**
 * 天气数据类
 */
public class WeatherResponse extends BaseResponse {

    private WeatherData retData;

    public WeatherData getRetData() {
        return retData;
    }

    public void setRetData(WeatherData retData) {
        this.retData = retData;
    }
}
