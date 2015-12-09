package com.plusend.weather.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plusend.weather.global.Constants;
import com.plusend.weather.bean.City;
import com.plusend.weather.db.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * 获取城市信息
 * Created by plusend on 15/11/10.
 */
public class CityHelper {

    private static final String TAG = CityHelper.class.getSimpleName();
    private Context context;

    public CityHelper(Context context) {
        this.context = context;
    }

    public ArrayList<City> getResultCityList(String keyword) {
        DBHelper dbHelper = new DBHelper(context);
        ArrayList<City> list = new ArrayList<>();
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(
                    "select * from city where county_en = \"" + keyword
                            + "\" or county_cn = \"" + keyword + "\" or city = \"" + keyword + "\" or province = \"" + keyword + "\"", null);
            Log.d("info", "length = " + cursor.getCount());
            while (cursor.moveToNext()) {
                City city = new City();
                city.setCityId(cursor.getString(0));
                city.setCountyEn(cursor.getString(1));
                city.setCountyCn(cursor.getString(2));
                city.setCity(cursor.getString(3));
                city.setProvince(cursor.getString(4));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<City> getCityList() {
        DBHelper dbHelper = new DBHelper(context);
        ArrayList<City> list = new ArrayList<>();
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from city", null);
            Log.d(TAG, "cursor.size:" + cursor.getCount());
            while (cursor.moveToNext()) {
                City city = new City();
                city.setCityId(cursor.getString(0));
                city.setCountyEn(cursor.getString(1));
                city.setCountyCn(cursor.getString(2));
                city.setCity(cursor.getString(3));
                city.setProvince(cursor.getString(4));
                list.add(city);
            }
            cursor.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param cityId :城市ID
     * @return 返回结果
     */
    public static String request(String cityId) {

        String httpUrl = Constants.API + "cityid=" + cityId + "&key=" + Constants.KEY;

        BufferedReader reader;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
