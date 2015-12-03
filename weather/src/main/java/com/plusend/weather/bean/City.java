package com.plusend.weather.bean;

/**
 * 城市信息类
 * Created by plusend on 15/11/9.
 */
public class City {
    private String cityId;
    private String countyEn;
    private String countyCn;
    private String city;
    private String province;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyEn() {
        return countyEn;
    }

    public void setCountyEn(String countyEn) {
        this.countyEn = countyEn;
    }

    public String getCountyCn() {
        return countyCn;
    }

    public void setCountyCn(String countyCn) {
        this.countyCn = countyCn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        String cityName = getCountyCn() + "," + getCity() + "," + getProvince();
        return cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId='" + cityId + '\'' +
                ", countyEn='" + countyEn + '\'' +
                ", countyCn='" + countyCn + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
