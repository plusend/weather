package com.plusend.weather;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by plusend on 15/11/9.
 */
public class WeatherApplication extends Application {
    private SharedPreferences mSharedPreferences;
    private static int citSize;

    @Override
    public void onCreate() {
        mSharedPreferences = getApplicationContext().getSharedPreferences("city", Context.MODE_PRIVATE);
        super.onCreate();
    }
}
