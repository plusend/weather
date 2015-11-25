package com.plusend.weather.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.astuetz.PagerSlidingTabStrip;
import com.plusend.weather.R;
import com.plusend.weather.adapter.WeatherPagerAdapter;
import com.plusend.weather.bean.City;
import com.plusend.weather.data.CityHelper;
import com.plusend.weather.global.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RelativeLayout rl_main;
    private Toolbar toolbar;
    private RecyclerView rv_search;
    private ViewPager vp;
    private PagerSlidingTabStrip tabs;
    private ImageView iv_delete;

    public static String cityId;
    private List<City> cityList = new ArrayList<>();
    private List<String> resultList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private SearchAdapter mSearchAdapter;
    private SharedPreferences mSharedPreferences;
    private Map<String, String> cityMap = new HashMap<>();
    private WeatherPagerAdapter pagerAdapter;
    List<City> tempList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = MainActivity.this.getSharedPreferences("city", Context.MODE_PRIVATE);
        findViewById();

        //获取城市列表
        int size = mSharedPreferences.getInt(Constant.CITY, 0);
        Log.d(TAG, "citySize:" + size);
        if (size > 0) {
            for (int i = 1; i <= size; i++) {
                String cityString = mSharedPreferences.getString(String.valueOf(i), "");
                Log.d(TAG, "cityString:" + cityString);
                City city = JSON.parseObject(cityString, City.class);
                cityId = city.getCityId();
                cityId = mSharedPreferences.getString(String.valueOf(i), "");
                Log.d(TAG, "cityId:" + cityId);
                tempList.add(city);
            }
            pagerAdapter.setCityList(tempList);
            vp.setAdapter(pagerAdapter);
            tabs.setViewPager(vp);
            iv_delete.setVisibility(View.VISIBLE);
        } else {
            iv_delete.setVisibility(View.GONE);
        }
        // testDb();
    }

    private void testDb() {
        List<City> cityList = new CityHelper(this).getResultCityList("chaoyang");
        for (City city : cityList) {
            Log.d(TAG, "city:" + city);
        }
    }

    private void findViewById() {
        rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 替换系统ActionBar
        setSupportActionBar(toolbar);
        rv_search = (RecyclerView) findViewById(R.id.rv_search);
        layoutManager = new LinearLayoutManager(this);
        rv_search.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv_search.setHasFixedSize(true);
        mSearchAdapter = new SearchAdapter(cityList);
        mSearchAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获得当前已选择城市的数量
                int size = mSharedPreferences.getInt("citySize", 0);
                City city = (City) v.getTag();
                String cityJson = JSON.toJSONString(city);
                Log.d(TAG, "cityJson:" + cityJson);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(String.valueOf(size + 1), cityJson);
                editor.putInt(Constant.CITY, size + 1);
                editor.apply();

                tempList.add(city);
                pagerAdapter.setCityList(tempList);
                pagerAdapter.notifyDataSetChanged();

                cityList.clear();
                mSearchAdapter.notifyDataSetChanged();
                rl_main.setVisibility(View.VISIBLE);
                tabs.setVisibility(View.VISIBLE);
                vp.setVisibility(View.VISIBLE);
                rv_search.setVisibility(View.GONE);

            }
        });
        rv_search.setAdapter(mSearchAdapter);
        vp = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mSharedPreferences.getInt("citySize", 0);
                if (size > 0) {
                    int position = vp.getCurrentItem();
                    Log.d(TAG, "position:" + position);

                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.remove(String.valueOf(position + 1));
                    editor.putInt(Constant.CITY, size - 1);
                    editor.apply();

                    tempList.remove(position);
                    pagerAdapter.setCityList(tempList);
                    pagerAdapter.notifyDataSetChanged();
                }
                if (size == 1) {
                    iv_delete.setVisibility(View.GONE);
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange");
                if (!TextUtils.isEmpty(newText)) {
                    cityList = new CityHelper(MainActivity.this).getResultCityList(newText);
                    mSearchAdapter.update(cityList);
                    rl_main.setVisibility(View.GONE);
                    tabs.setVisibility(View.GONE);
                    vp.setVisibility(View.GONE);
                    rv_search.setVisibility(View.VISIBLE);
                } else {
                    cityList.clear();
                    mSearchAdapter.notifyDataSetChanged();
                    rl_main.setVisibility(View.VISIBLE);
                    tabs.setVisibility(View.VISIBLE);
                    vp.setVisibility(View.VISIBLE);
                    rv_search.setVisibility(View.GONE);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

}
