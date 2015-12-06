package com.plusend.weather.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.astuetz.PagerSlidingTabStrip;
import com.plusend.weather.R;
import com.plusend.weather.adapter.SearchAdapter;
import com.plusend.weather.adapter.WeatherPagerAdapter;
import com.plusend.weather.bean.City;
import com.plusend.weather.data.CityHelper;
import com.plusend.weather.global.Constants;
import com.plusend.weather.global.Util;
import com.squareup.okhttp.Request;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import im.fir.sdk.FIR;
import im.fir.sdk.callback.VersionCheckCallback;
import im.fir.sdk.version.AppVersion;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RelativeLayout rl_main;
    private RecyclerView rv_search;
    private ViewPager vp;
    private PagerSlidingTabStrip tabs;
    private ImageView iv_delete;

    private List<City> cityList = new ArrayList<>();// 搜索结果
    private SearchAdapter mSearchAdapter;
    private SharedPreferences mSharedPreferences;
    private WeatherPagerAdapter pagerAdapter;
    private List<City> tempList = new ArrayList<>();// 已选城市列表
    private long downloadId = -1;// 下载Id
    private String downloadFile;// 新版本名字
    private CompleteReceiver completeReceiver;// 下载监听器
    private final Object object = new Object(); // 加锁对象

    private IWXAPI wxAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = MainActivity.this.getSharedPreferences("city", Context.MODE_PRIVATE);
        // 微信分享
        wxAPI = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        wxAPI.registerApp(Constants.APP_ID);

        findViewById();

        setListener();

    }

    private void findViewById() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        int size = mSharedPreferences.getInt(Constants.CITY, 0);
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
                editor.putInt(Constants.CITY, size - 1);
                editor.apply();

                tempList.remove(position);
                pagerAdapter.updateUI(tempList);

            }
        });

        // 搜索
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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
                editor.putInt(Constants.CITY, size + 1);
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
        // 搜索
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:

                // 分享到微信
                Bitmap bitmap = Util.getScreen(MainActivity.this);
                WXImageObject imageObject = new WXImageObject(bitmap);

                WXMediaMessage wxMsg = new WXMediaMessage();
                wxMsg.mediaObject = imageObject;

                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                bitmap.recycle();
                wxMsg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                SendMessageToWX.Req wxReq = new SendMessageToWX.Req();
                wxReq.transaction = String.valueOf(System.currentTimeMillis());
                wxReq.message = wxMsg;
                wxReq.scene = SendMessageToWX.Req.WXSceneTimeline;

                wxAPI.sendReq(wxReq);
                break;
            case R.id.setting:
                FIR.checkForUpdateInFIR(Constants.FIR_API_TOKEN, new VersionCheckCallback() {

                    @Override
                    public void onSuccess(AppVersion appVersion, boolean b) {
                        Log.d(TAG, "check success");
                        Log.d(TAG, "appVersion:" + appVersion);
                        // 防止同时下载
                        synchronized (object) {
                            if (downloadId == -1) {
                                // 获取当前版本号
                                int version = Util.getVersionCode(MainActivity.this);

                                if (appVersion.getVersionCode() > version) {
                                    // 下载新版本
                                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(appVersion.getUpdateUrl()));
                                    downloadFile = "KeyboardWeather.apk";
                                    request.setDestinationInExternalPublicDir("KeyboardWeather", downloadFile);
                                    // Wi-Fi下载
                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                                    downloadId = downloadManager.enqueue(request);
                                    Toast.makeText(MainActivity.this, R.string.version_success, Toast.LENGTH_SHORT).show();

                                    // 注册下载完成监听器
                                    completeReceiver = new CompleteReceiver();
                                    /** register download success broadcast **/
                                    registerReceiver(completeReceiver,
                                            new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                } else {
                                    Toast.makeText(MainActivity.this, R.string.version_none, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        super.onSuccess(appVersion, b);
                    }

                    @Override
                    public void onFail(Request request, Exception e) {
                        Log.d(TAG, "check fail;");
                        super.onFail(request, e);
                    }

                    @Override
                    public void onStart() {
                        Log.d(TAG, "check start;");
                        super.onStart();
                    }

                    @Override
                    public void onFinish() {
                        Log.d(TAG, "check finish;");
                        super.onFinish();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 下载完成广播接受者
    class CompleteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取下载Id
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadId == completeDownloadId) {
                // 安装新版本
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                installIntent.setDataAndType(downloadManager.getUriForDownloadedFile(downloadId),
                        "application/vnd.android.package-archive");
                MainActivity.this.startActivity(installIntent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消下载完成监听
        if (completeReceiver != null) {
            unregisterReceiver(completeReceiver);
        }
    }
}
