package com.plusend.weather.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.plusend.weather.global.Constants;
import com.plusend.weather.R;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;

public class SplashAdActivity extends AppCompatActivity implements SplashADListener {

    private static final String TAG = "SplashAdActivity";

    private SplashAD splashAD;
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ad);
        container = (ViewGroup) this.findViewById(R.id.splash_container);
        splashAD = new SplashAD(this, container, Constants.GDT_APP_ID, Constants.SPLASH_POS_ID, this);
    }

    @Override
    public void onADPresent() {
        Log.i(TAG, "SplashADPresent");
    }

    @Override
    public void onADDismissed() {
        Log.i(TAG, "SplashADDismissed");
        next();
    }

    private void next() {
        this.startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    public void onNoAD(int arg0) {
        Log.i(TAG, "LoadSplashADFail,ecode=" + arg0);
        next();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
