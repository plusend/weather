package com.plusend.weather.bean;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by plusend on 15/11/6.
 */
public class Weather {


    /**
     * aqi : {"city":{"aqi":"32","co":"0","no2":"24","o3":"24","pm10":"0","pm25":"22","qlty":"优","so2":"3"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2015-11-05 21:52","utc":"2015-11-05 13:52"}}
     * daily_forecast : [{"astro":{"sr":"06:47","ss":"17:08"},"cond":{"code_d":"309","code_n":"404","txt_d":"毛毛雨/细雨","txt_n":"雨夹雪"},"date":"2015-11-05","hum":"53","pcpn":"3.9","pop":"97","pres":"1031","tmp":{"max":"11","min":"2"},"vis":"2","wind":{"deg":"123","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:48","ss":"17:07"},"cond":{"code_d":"404","code_n":"404","txt_d":"雨夹雪","txt_n":"雨夹雪"},"date":"2015-11-06","hum":"85","pcpn":"9.9","pop":"92","pres":"1032","tmp":{"max":"4","min":"2"},"vis":"9","wind":{"deg":"77","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:49","ss":"17:06"},"cond":{"code_d":"104","code_n":"305","txt_d":"阴","txt_n":"小雨"},"date":"2015-11-07","hum":"76","pcpn":"3.2","pop":"61","pres":"1028","tmp":{"max":"7","min":"5"},"vis":"10","wind":{"deg":"71","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:50","ss":"17:05"},"cond":{"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"},"date":"2015-11-08","hum":"76","pcpn":"2.0","pop":"74","pres":"1026","tmp":{"max":"11","min":"2"},"vis":"10","wind":{"deg":"145","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:51","ss":"17:04"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2015-11-09","hum":"59","pcpn":"0.0","pop":"0","pres":"1025","tmp":{"max":"14","min":"3"},"vis":"10","wind":{"deg":"177","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:53","ss":"17:03"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2015-11-10","hum":"59","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"12","min":"6"},"vis":"10","wind":{"deg":"158","dir":"无持续风向","sc":"微风","spd":"26"}},{"astro":{"sr":"06:54","ss":"17:02"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2015-11-11","hum":"77","pcpn":"6.1","pop":"43","pres":"1028","tmp":{"max":"11","min":"5"},"vis":"10","wind":{"deg":"134","dir":"无持续风向","sc":"微风","spd":"26"}}]
     * hourly_forecast : [{"date":"2015-11-05 22:00","hum":"51","pop":"93","pres":"1034","tmp":"6","wind":{"deg":"251","dir":"西南风","sc":"微风","spd":"8"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"0","hum":"73","pcpn":"0","pres":"1033","tmp":"4","vis":"10","wind":{"deg":"40","dir":"东北风","sc":"5-6","spd":"19"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较不舒适","txt":"白天会有降雪发生，您会感觉偏冷，不很舒适，请注意添加衣物。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},"flu":{"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。"},"sport":{"brf":"较不宜","txt":"有降雪，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适运动并注意保暖。"},"trav":{"brf":"适宜","txt":"有降雪，出行请注意携带雨具。这样的天气让人感觉稍微有点凉，但还是比较适宜旅游。出行请注意安全。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
     */

    @JSONField(name = "HeWeather data service 3.0")
    private List<HeWeather> heWeather;

    public void setHeWeather(List<HeWeather> heWeather) {
        this.heWeather = heWeather;
    }

    public List<HeWeather> getHeWeather() {
        return heWeather;
    }

    public static class HeWeather {
        @Override
        public String toString() {
            return "HeWeather{" +
                    "aqi=" + aqi +
                    ", basic=" + basic +
                    ", now=" + now +
                    ", status='" + status + '\'' +
                    ", suggestion=" + suggestion +
                    ", dailyForecast=" + dailyForecast +
                    ", hourlyForecast=" + hourlyForecast +
                    '}';
        }

        /**
         * city : {"aqi":"32","co":"0","no2":"24","o3":"24","pm10":"0","pm25":"22","qlty":"优","so2":"3"}
         */

        @JSONField(name = "aqi")
        private AqiEntity aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2015-11-05 21:52","utc":"2015-11-05 13:52"}
         */

        @JSONField(name = "basic")
        private BasicEntity basic;
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 0
         * hum : 73
         * pcpn : 0
         * pres : 1033
         * tmp : 4
         * vis : 10
         * wind : {"deg":"40","dir":"东北风","sc":"5-6","spd":"19"}
         */

        @JSONField(name = "now")
        private NowEntity now;
        @JSONField(name = "status")
        private String status;
        /**
         * comf : {"brf":"较不舒适","txt":"白天会有降雪发生，您会感觉偏冷，不很舒适，请注意添加衣物。"}
         * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
         * drsg : {"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"}
         * flu : {"brf":"极易发","txt":"将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。"}
         * sport : {"brf":"较不宜","txt":"有降雪，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适运动并注意保暖。"}
         * trav : {"brf":"适宜","txt":"有降雪，出行请注意携带雨具。这样的天气让人感觉稍微有点凉，但还是比较适宜旅游。出行请注意安全。"}
         * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
         */

        @JSONField(name = "suggestion")
        private SuggestionEntity suggestion;
        /**
         * astro : {"sr":"06:47","ss":"17:08"}
         * cond : {"code_d":"309","code_n":"404","txt_d":"毛毛雨/细雨","txt_n":"雨夹雪"}
         * date : 2015-11-05
         * hum : 53
         * pcpn : 3.9
         * pop : 97
         * pres : 1031
         * tmp : {"max":"11","min":"2"}
         * vis : 2
         * wind : {"deg":"123","dir":"无持续风向","sc":"微风","spd":"26"}
         */

        @JSONField(name = "daily_forecast")
        private List<DailyForecastEntity> dailyForecast;
        /**
         * date : 2015-11-05 22:00
         * hum : 51
         * pop : 93
         * pres : 1034
         * tmp : 6
         * wind : {"deg":"251","dir":"西南风","sc":"微风","spd":"8"}
         */

        @JSONField(name = "hourly_forecast")
        private List<HourlyForecastEntity> hourlyForecast;

        public void setAqi(AqiEntity aqi) {
            this.aqi = aqi;
        }

        public void setBasic(BasicEntity basic) {
            this.basic = basic;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public void setDailyForecast(List<DailyForecastEntity> dailyForecast) {
            this.dailyForecast = dailyForecast;
        }

        public void setHourlyForecast(List<HourlyForecastEntity> hourlyForecast) {
            this.hourlyForecast = hourlyForecast;
        }

        public AqiEntity getAqi() {
            return aqi;
        }

        public BasicEntity getBasic() {
            return basic;
        }

        public NowEntity getNow() {
            return now;
        }

        public String getStatus() {
            return status;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public List<DailyForecastEntity> getDailyForecast() {
            return dailyForecast;
        }

        public List<HourlyForecastEntity> getHourlyForecast() {
            return hourlyForecast;
        }

        public static class AqiEntity {
            @Override
            public String toString() {
                return "AqiEntity{" +
                        "city=" + city +
                        '}';
            }

            /**
             * aqi : 32
             * co : 0
             * no2 : 24
             * o3 : 24
             * pm10 : 0
             * pm25 : 22
             * qlty : 优
             * so2 : 3
             */

            @JSONField(name = "city")
            private CityEntity city;

            public void setCity(CityEntity city) {
                this.city = city;
            }

            public CityEntity getCity() {
                return city;
            }

            public static class CityEntity {
                @Override
                public String toString() {
                    return "CityEntity{" +
                            "aqi='" + aqi + '\'' +
                            ", co='" + co + '\'' +
                            ", no2='" + no2 + '\'' +
                            ", o3='" + o3 + '\'' +
                            ", pm10='" + pm10 + '\'' +
                            ", pm25='" + pm25 + '\'' +
                            ", qlty='" + qlty + '\'' +
                            ", so2='" + so2 + '\'' +
                            '}';
                }

                @JSONField(name = "aqi")
                private String aqi;
                @JSONField(name = "co")
                private String co;
                @JSONField(name = "no2")
                private String no2;
                @JSONField(name = "o3")
                private String o3;
                @JSONField(name = "pm10")
                private String pm10;
                @JSONField(name = "pm25")
                private String pm25;
                @JSONField(name = "qlty")
                private String qlty;
                @JSONField(name = "so2")
                private String so2;

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getAqi() {
                    return aqi;
                }

                public String getCo() {
                    return co;
                }

                public String getNo2() {
                    return no2;
                }

                public String getO3() {
                    return o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public String getSo2() {
                    return so2;
                }
            }
        }

        public static class BasicEntity {
            @Override
            public String toString() {
                return "BasicEntity{" +
                        "city='" + city + '\'' +
                        ", cnty='" + cnty + '\'' +
                        ", id='" + id + '\'' +
                        ", lat='" + lat + '\'' +
                        ", lon='" + lon + '\'' +
                        ", update=" + update +
                        '}';
            }

            @JSONField(name = "city")
            private String city;
            @JSONField(name = "cnty")
            private String cnty;
            @JSONField(name = "id")
            private String id;
            @JSONField(name = "lat")
            private String lat;
            @JSONField(name = "lon")
            private String lon;
            /**
             * loc : 2015-11-05 21:52
             * utc : 2015-11-05 13:52
             */

            @JSONField(name = "update")
            private UpdateEntity update;

            public void setCity(String city) {
                this.city = city;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public void setUpdate(UpdateEntity update) {
                this.update = update;
            }

            public String getCity() {
                return city;
            }

            public String getCnty() {
                return cnty;
            }

            public String getId() {
                return id;
            }

            public String getLat() {
                return lat;
            }

            public String getLon() {
                return lon;
            }

            public UpdateEntity getUpdate() {
                return update;
            }

            public static class UpdateEntity {
                @JSONField(name = "loc")
                private String loc;
                @JSONField(name = "utc")
                private String utc;

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }

                public String getLoc() {
                    return loc;
                }

                public String getUtc() {
                    return utc;
                }
            }
        }

        public static class NowEntity {
            @Override
            public String toString() {
                return "NowEntity{" +
                        "cond=" + cond +
                        ", fl='" + fl + '\'' +
                        ", hum='" + hum + '\'' +
                        ", pcpn='" + pcpn + '\'' +
                        ", pres='" + pres + '\'' +
                        ", tmp='" + tmp + '\'' +
                        ", vis='" + vis + '\'' +
                        ", wind=" + wind +
                        '}';
            }

            /**
             * code : 101
             * txt : 多云
             */

            @JSONField(name = "cond")
            private CondEntity cond;
            @JSONField(name = "fl")
            private String fl;
            @JSONField(name = "hum")
            private String hum;
            @JSONField(name = "pcpn")
            private String pcpn;
            @JSONField(name = "pres")
            private String pres;
            @JSONField(name = "tmp")
            private String tmp;
            @JSONField(name = "vis")
            private String vis;
            /**
             * deg : 40
             * dir : 东北风
             * sc : 5-6
             * spd : 19
             */

            @JSONField(name = "wind")
            private WindEntity wind;

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getFl() {
                return fl;
            }

            public String getHum() {
                return hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public String getPres() {
                return pres;
            }

            public String getTmp() {
                return tmp;
            }

            public String getVis() {
                return vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class CondEntity {
                @Override
                public String toString() {
                    return "CondEntity{" +
                            "code='" + code + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "code")
                private String code;
                @JSONField(name = "txt")
                private String txt;

                public void setCode(String code) {
                    this.code = code;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getCode() {
                    return code;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class WindEntity {
                @Override
                public String toString() {
                    return "WindEntity{" +
                            "deg='" + deg + '\'' +
                            ", dir='" + dir + '\'' +
                            ", sc='" + sc + '\'' +
                            ", spd='" + spd + '\'' +
                            '}';
                }

                @JSONField(name = "deg")
                private String deg;
                @JSONField(name = "dir")
                private String dir;
                @JSONField(name = "sc")
                private String sc;
                @JSONField(name = "spd")
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }
            }
        }

        public static class SuggestionEntity {
            @Override
            public String toString() {
                return "SuggestionEntity{" +
                        "comf=" + comf +
                        ", cw=" + cw +
                        ", drsg=" + drsg +
                        ", flu=" + flu +
                        ", sport=" + sport +
                        ", trav=" + trav +
                        ", uv=" + uv +
                        '}';
            }

            /**
             * brf : 较不舒适
             * txt : 白天会有降雪发生，您会感觉偏冷，不很舒适，请注意添加衣物。
             */

            @JSONField(name = "comf")
            private ComfEntity comf;
            /**
             * brf : 不宜
             * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
             */

            @JSONField(name = "cw")
            private CwEntity cw;
            /**
             * brf : 冷
             * txt : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
             */

            @JSONField(name = "drsg")
            private DrsgEntity drsg;
            /**
             * brf : 极易发
             * txt : 将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。
             */

            @JSONField(name = "flu")
            private FluEntity flu;
            /**
             * brf : 较不宜
             * txt : 有降雪，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适运动并注意保暖。
             */

            @JSONField(name = "sport")
            private SportEntity sport;
            /**
             * brf : 适宜
             * txt : 有降雪，出行请注意携带雨具。这样的天气让人感觉稍微有点凉，但还是比较适宜旅游。出行请注意安全。
             */

            @JSONField(name = "trav")
            private TravEntity trav;
            /**
             * brf : 最弱
             * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
             */

            @JSONField(name = "uv")
            private UvEntity uv;

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public ComfEntity getComf() {
                return comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public static class ComfEntity {
                @Override
                public String toString() {
                    return "ComfEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class CwEntity {
                @Override
                public String toString() {
                    return "CwEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class DrsgEntity {
                @Override
                public String toString() {
                    return "DrsgEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class FluEntity {
                @Override
                public String toString() {
                    return "FluEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class SportEntity {
                @Override
                public String toString() {
                    return "SportEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class TravEntity {
                @Override
                public String toString() {
                    return "TravEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class UvEntity {
                @Override
                public String toString() {
                    return "UvEntity{" +
                            "brf='" + brf + '\'' +
                            ", txt='" + txt + '\'' +
                            '}';
                }

                @JSONField(name = "brf")
                private String brf;
                @JSONField(name = "txt")
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }
        }

        public static class DailyForecastEntity {
            @Override
            public String toString() {
                return "DailyForecastEntity{" +
                        "astro=" + astro +
                        ", cond=" + cond +
                        ", date='" + date + '\'' +
                        ", hum='" + hum + '\'' +
                        ", pcpn='" + pcpn + '\'' +
                        ", pop='" + pop + '\'' +
                        ", pres='" + pres + '\'' +
                        ", tmp=" + tmp +
                        ", vis='" + vis + '\'' +
                        ", wind=" + wind +
                        '}';
            }

            /**
             * sr : 06:47
             * ss : 17:08
             */

            @JSONField(name = "astro")
            private AstroEntity astro;
            /**
             * code_d : 309
             * code_n : 404
             * txt_d : 毛毛雨/细雨
             * txt_n : 雨夹雪
             */

            @JSONField(name = "cond")
            private CondEntity cond;
            @JSONField(name = "date")
            private String date;
            @JSONField(name = "hum")
            private String hum;
            @JSONField(name = "pcpn")
            private String pcpn;
            @JSONField(name = "pop")
            private String pop;
            @JSONField(name = "pres")
            private String pres;
            /**
             * max : 11
             * min : 2
             */

            @JSONField(name = "tmp")
            private TmpEntity tmp;
            @JSONField(name = "vis")
            private String vis;
            /**
             * deg : 123
             * dir : 无持续风向
             * sc : 微风
             * spd : 26
             */

            @JSONField(name = "wind")
            private WindEntity wind;

            public void setAstro(AstroEntity astro) {
                this.astro = astro;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public AstroEntity getAstro() {
                return astro;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getDate() {
                return date;
            }

            public String getHum() {
                return hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public String getPop() {
                return pop;
            }

            public String getPres() {
                return pres;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public String getVis() {
                return vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class AstroEntity {
                @Override
                public String toString() {
                    return "AstroEntity{" +
                            "sr='" + sr + '\'' +
                            ", ss='" + ss + '\'' +
                            '}';
                }

                @JSONField(name = "sr")
                private String sr;
                @JSONField(name = "ss")
                private String ss;

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }

                public String getSr() {
                    return sr;
                }

                public String getSs() {
                    return ss;
                }
            }

            public static class CondEntity {
                @Override
                public String toString() {
                    return "CondEntity{" +
                            "codeD='" + codeD + '\'' +
                            ", codeN='" + codeN + '\'' +
                            ", txtD='" + txtD + '\'' +
                            ", txtN='" + txtN + '\'' +
                            '}';
                }

                @JSONField(name = "code_d")
                private String codeD;
                @JSONField(name = "code_n")
                private String codeN;
                @JSONField(name = "txt_d")
                private String txtD;
                @JSONField(name = "txt_n")
                private String txtN;

                public void setCodeD(String codeD) {
                    this.codeD = codeD;
                }

                public void setCodeN(String codeN) {
                    this.codeN = codeN;
                }

                public void setTxtD(String txtD) {
                    this.txtD = txtD;
                }

                public void setTxtN(String txtN) {
                    this.txtN = txtN;
                }

                public String getCodeD() {
                    return codeD;
                }

                public String getCodeN() {
                    return codeN;
                }

                public String getTxtD() {
                    return txtD;
                }

                public String getTxtN() {
                    return txtN;
                }
            }

            public static class TmpEntity {
                @Override
                public String toString() {
                    return "TmpEntity{" +
                            "max='" + max + '\'' +
                            ", min='" + min + '\'' +
                            '}';
                }

                @JSONField(name = "max")
                private String max;
                @JSONField(name = "min")
                private String min;

                public void setMax(String max) {
                    this.max = max;
                }

                public void setMin(String min) {
                    this.min = min;
                }

                public String getMax() {
                    return max;
                }

                public String getMin() {
                    return min;
                }
            }

            public static class WindEntity {
                @Override
                public String toString() {
                    return "WindEntity{" +
                            "deg='" + deg + '\'' +
                            ", dir='" + dir + '\'' +
                            ", sc='" + sc + '\'' +
                            ", spd='" + spd + '\'' +
                            '}';
                }

                @JSONField(name = "deg")
                private String deg;
                @JSONField(name = "dir")
                private String dir;
                @JSONField(name = "sc")
                private String sc;
                @JSONField(name = "spd")
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }
            }
        }

        public static class HourlyForecastEntity {
            @Override
            public String toString() {
                return "HourlyForecastEntity{" +
                        "date='" + date + '\'' +
                        ", hum='" + hum + '\'' +
                        ", pop='" + pop + '\'' +
                        ", pres='" + pres + '\'' +
                        ", tmp='" + tmp + '\'' +
                        ", wind=" + wind +
                        '}';
            }

            @JSONField(name = "date")
            private String date;
            @JSONField(name = "hum")
            private String hum;
            @JSONField(name = "pop")
            private String pop;
            @JSONField(name = "pres")
            private String pres;
            @JSONField(name = "tmp")
            private String tmp;
            /**
             * deg : 251
             * dir : 西南风
             * sc : 微风
             * spd : 8
             */

            @JSONField(name = "wind")
            private WindEntity wind;

            public void setDate(String date) {
                this.date = date;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public String getDate() {
                return date;
            }

            public String getHum() {
                return hum;
            }

            public String getPop() {
                return pop;
            }

            public String getPres() {
                return pres;
            }

            public String getTmp() {
                return tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class WindEntity {
                @JSONField(name = "deg")
                private String deg;
                @JSONField(name = "dir")
                private String dir;
                @JSONField(name = "sc")
                private String sc;
                @JSONField(name = "spd")
                private String spd;

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }

                @Override
                public String toString() {
                    return "WindEntity{" +
                            "deg='" + deg + '\'' +
                            ", dir='" + dir + '\'' +
                            ", sc='" + sc + '\'' +
                            ", spd='" + spd + '\'' +
                            '}';
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "heWeather=" + heWeather +
                '}';
    }
}
