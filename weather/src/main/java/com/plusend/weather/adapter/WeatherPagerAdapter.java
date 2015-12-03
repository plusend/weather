package com.plusend.weather.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.plusend.weather.bean.City;
import com.plusend.weather.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plusend on 15/11/16.
 */
public class WeatherPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "WeatherPagerAdapter";

    private List<City> cityList = new ArrayList<>();

    public WeatherPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setCityList(List<City> list) {
        this.cityList = list;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "count:" + cityList.size());
        return cityList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name = cityList.get(position).getName();
        int i = name.indexOf(",");
        return cityList.get(position).getName().substring(0, i);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putString("cityId", cityList.get(position).getCityId());
        return WeatherFragment.getInstance(args);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void updateUI(List<City> list){
        this.cityList = list;
        notifyDataSetChanged();
    }
}
