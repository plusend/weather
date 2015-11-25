package com.plusend.weather.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.plusend.weather.R;
import com.plusend.weather.bean.Weather;
import com.plusend.weather.data.CityHelper;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by plusend on 15/11/16.
 */
public class WeatherFragment extends Fragment {

    private static final String TAG = "WeatherFragment";

    private TextView tv_tmp;
    private TextView tv_scope;
    private TextView tv_weather;
    private TextView tv_pm25;
    private ImageView iv_weather;
    private SwipeRefreshLayout srl_main;

    public String cityId;

    public static WeatherFragment getInstance(Bundle args) {
        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.setArguments(args);
        return weatherFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = getArguments();
        this.cityId = args.getString("cityId");

        Log.d(TAG, "cityId:" + cityId);

        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        tv_tmp = (TextView) root.findViewById(R.id.tv_tmp);
        tv_weather = (TextView) root.findViewById(R.id.tv_weather);
        tv_scope = (TextView) root.findViewById(R.id.tv_tmp_scope);
        tv_pm25 = (TextView) root.findViewById(R.id.tv_pm25);
        iv_weather = (ImageView) root.findViewById(R.id.iv_weather);
        srl_main = (SwipeRefreshLayout) root.findViewById(R.id.srl_main);
        srl_main.setColorSchemeColors(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryBack);
        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeather();
            }
        });
        getWeather();
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void getWeather() {

        rx.Observable.create(new rx.Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

                String jsonResult = CityHelper.request(cityId);
                subscriber.onNext(jsonResult);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String result) {
                        Log.d(TAG, "result:" + result);

                        Weather weather = JSON.parseObject(result, Weather.class);
                        Log.d(TAG, "weather:" + weather);

                        if ("ok".equals(weather.getHeWeather().get(0).getStatus())) {
                            srl_main.setRefreshing(false);
                            updateUI(weather.getHeWeather().get(0));
                        }
                    }

                    @Override
                    public void onCompleted() {
                        Toast.makeText(getActivity(), "Updated!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Something error!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void updateUI(Weather.HeWeather weather) {
        tv_tmp.setText(weather.getNow().getTmp() + "°");
        String icon = "icon" + weather.getNow().getCond().getCode();
        int ivWeatherId = getResources().getIdentifier(icon, "mipmap", "com.plusend.weather");
        iv_weather.setImageResource(ivWeatherId);
        tv_weather.setText(weather.getNow().getCond().getTxt());
        tv_pm25.setText("PM2.5 " + weather.getAqi().getCity().getPm25());
    }
}
