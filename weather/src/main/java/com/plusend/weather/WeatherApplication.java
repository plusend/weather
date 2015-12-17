package com.plusend.weather;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import im.fir.sdk.FIR;

/**
 * Created by plusend on 15/11/9.
 */
public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FIR.init(this);
    }
}
