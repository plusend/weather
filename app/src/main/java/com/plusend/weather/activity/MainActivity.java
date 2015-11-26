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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RelativeLayout rl_main;
    private Toolbar toolbar;
    private RecyclerView rv_search;
    private ViewPager vp;
    private PagerSlidingTabStrip tabs;
    private ImageView iv_delete;

    public static String cityId;
    private List<City> cityList = new ArrayList<>();//搜索结果
    private RecyclerView.LayoutManager layoutManager;
    private SearchAdapter mSearchAdapter;
    private SharedPreferences mSharedPreferences;
    private WeatherPagerAdapter pagerAdapter;
    private List<City> tempList = new ArrayList<>();//已选城市列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = MainActivity.this.getSharedPreferences("city", Context.MODE_PRIVATE);

        findViewById();

        setListener();

        // testDb();
    }

    private void testDb() {
        List<City> cityList = new CityHelper(this).getResultCityList("chaoyang");
        for (City city : cityList) {
            Log.d(TAG, "city:" + city);
        }
    }

    private void findViewById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 替换系统ActionBar
        setSupportActionBar(toolbar);

        rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        vp = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);

        // 搜索
        rv_search = (RecyclerView) findViewById(R.id.rv_search);
    }

    private void setListener() {

        pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());
        //获取城市列表
        int size = mSharedPreferences.getInt(Constant.CITY, 0);
        Log.d(TAG, "citySize:" + size);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String cityString = mSharedPreferences.getString(String.valueOf(i), "");
                Log.d(TAG, "cityString:" + cityString);
                City city = JSON.parseObject(cityString, City.class);
                tempList.add(city);
            }

            iv_delete.setVisibility(View.VISIBLE);
        } else {
            iv_delete.setVisibility(View.GONE);
        }

        pagerAdapter.setCityList(tempList);
        vp.setAdapter(pagerAdapter);
        tabs.setViewPager(vp);

        // 删除事件
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mSharedPreferences.getInt("citySize", 0);
                int position = vp.getCurrentItem();
                Log.d(TAG, "position:" + position);
                SharedPreferences.Editor editor = mSharedPreferences.edit();

                if (size == 1) {
                    iv_delete.setVisibility(View.GONE);
                }
                // size大于1的时候,将数据前移,覆盖position位置的数据,同时删除最后一个
                if (size > 1) {

                    for (int i = position; i < size - 1; i++) {
                        String next = mSharedPreferences.getString(String.valueOf(i + 1), "");
                        editor.putString(String.valueOf(i), next);
                    }
                }
                editor.remove(String.valueOf(size - 1));
                editor.putInt(Constant.CITY, size - 1);
                editor.apply();

                tempList.remove(position);
                pagerAdapter.updateUI(tempList);

            }
        });

        // 搜索
        layoutManager = new LinearLayoutManager(this);
        rv_search.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rv_search.setHasFixedSize(true);

        mSearchAdapter = new SearchAdapter(cityList);
        rv_search.setAdapter(mSearchAdapter);

        mSearchAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获得当前已选择城市的数量
                int size = mSharedPreferences.getInt("citySize", 0);
                City city = (City) v.getTag();
                String cityJson = JSON.toJSONString(city);
                Log.d(TAG, "cityJson:" + cityJson);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(String.valueOf(size), cityJson);
                editor.putInt(Constant.CITY, size + 1);
                editor.apply();

                tempList.add(city);
                pagerAdapter.updateUI(tempList);

                cityList.clear();
                mSearchAdapter.update(cityList);
                rv_search.setVisibility(View.GONE);

                rl_main.setVisibility(View.VISIBLE);
                tabs.setVisibility(View.VISIBLE);
                vp.setVisibility(View.VISIBLE);

                if (size == 0) {
                    iv_delete.setVisibility(View.VISIBLE);
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
                    mSearchAdapter.update(cityList);
                    rv_search.setVisibility(View.GONE);
                    rl_main.setVisibility(View.VISIBLE);
                    tabs.setVisibility(View.VISIBLE);
                    vp.setVisibility(View.VISIBLE);
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
