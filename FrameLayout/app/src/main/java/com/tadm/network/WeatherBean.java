package com.tadm.network;

import java.util.List;

public class WeatherBean {

    /**
     * status : 0
     * msg : ok
     * result : {"city":"巴中","cityid":324,"citycode":"101270901","date":"2020-06-03","week":"星期三","weather":"阴","temp":"19","temphigh":"28","templow":"17","img":"2","humidity":"88","pressure":"946","windspeed":"2.4","winddirect":"南风","windpower":"2级","updatetime":"2020-06-03 09:54:00","aqi":{"so2":"5","so224":"4","no2":"14","no224":"19","co":"0.400","co24":"0.430","o3":"25","o38":"16","o324":"18","pm10":"36","pm1024":"40","pm2_5":"24","pm2_524":"17","iso2":"2","ino2":"7","ico":"4","io3":"8","io38":"8","ipm10":"36","ipm2_5":"35","aqi":"36","primarypollutant":"aqi","quality":"优","timepoint":"2020-06-03 09:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}},"index":[{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"适宜","detail":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"iname":"紫外线指数","ivalue":"中等","detail":"属中等强度紫外线辐射天气，建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"},{"iname":"感冒指数","ivalue":"少发","detail":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},{"iname":"洗车指数","ivalue":"适宜","detail":"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。"},{"iname":"空气污染扩散指数","ivalue":"中","detail":"气象条件对空气污染物稀释、扩散和清除无明显影响。"},{"iname":"穿衣指数","ivalue":"热","detail":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}],"daily":[{"date":"2020-06-03","week":"星期三","sunrise":"05:47","sunset":"19:54","night":{"weather":"多云","templow":"17","img":"1","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"28","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-04","week":"星期四","sunrise":"05:47","sunset":"19:55","night":{"weather":"多云","templow":"19","img":"1","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"30","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-05","week":"星期五","sunrise":"05:47","sunset":"19:55","night":{"weather":"阴","templow":"21","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"晴","temphigh":"32","img":"0","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-06","week":"星期六","sunrise":"05:47","sunset":"19:56","night":{"weather":"阴","templow":"22","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"32","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-07","week":"星期日","sunrise":"05:46","sunset":"19:56","night":{"weather":"小雨","templow":"22","img":"7","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"30","img":"3","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-08","week":"星期一","sunrise":"05:46","sunset":"19:57","night":{"weather":"中雨","templow":"21","img":"8","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"29","img":"3","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-09","week":"星期二","sunrise":"05:46","sunset":"19:57","night":{"weather":"阵雨","templow":"22","img":"3","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"30","img":"3","winddirect":"微风","windpower":"3级"}}],"hourly":[{"time":"11:00","weather":"多云","temp":"23","img":"1"},{"time":"12:00","weather":"多云","temp":"25","img":"1"},{"time":"13:00","weather":"多云","temp":"26","img":"1"},{"time":"14:00","weather":"多云","temp":"27","img":"1"},{"time":"15:00","weather":"多云","temp":"28","img":"1"},{"time":"16:00","weather":"多云","temp":"28","img":"1"},{"time":"17:00","weather":"多云","temp":"28","img":"1"},{"time":"18:00","weather":"多云","temp":"26","img":"1"},{"time":"19:00","weather":"多云","temp":"25","img":"1"},{"time":"20:00","weather":"多云","temp":"23","img":"1"},{"time":"21:00","weather":"多云","temp":"22","img":"1"},{"time":"22:00","weather":"多云","temp":"21","img":"1"},{"time":"23:00","weather":"晴","temp":"20","img":"0"},{"time":"00:00","weather":"晴","temp":"20","img":"0"},{"time":"01:00","weather":"多云","temp":"19","img":"1"},{"time":"02:00","weather":"多云","temp":"19","img":"1"},{"time":"03:00","weather":"多云","temp":"18","img":"1"},{"time":"04:00","weather":"多云","temp":"18","img":"1"},{"time":"05:00","weather":"多云","temp":"17","img":"1"},{"time":"06:00","weather":"多云","temp":"18","img":"1"},{"time":"07:00","weather":"多云","temp":"20","img":"1"},{"time":"08:00","weather":"晴","temp":"21","img":"0"},{"time":"09:00","weather":"多云","temp":"22","img":"1"},{"time":"10:00","weather":"多云","temp":"24","img":"1"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * city : 巴中
         * cityid : 324
         * citycode : 101270901
         * date : 2020-06-03
         * week : 星期三
         * weather : 阴
         * temp : 19
         * temphigh : 28
         * templow : 17
         * img : 2
         * humidity : 88
         * pressure : 946
         * windspeed : 2.4
         * winddirect : 南风
         * windpower : 2级
         * updatetime : 2020-06-03 09:54:00
         * aqi : {"so2":"5","so224":"4","no2":"14","no224":"19","co":"0.400","co24":"0.430","o3":"25","o38":"16","o324":"18","pm10":"36","pm1024":"40","pm2_5":"24","pm2_524":"17","iso2":"2","ino2":"7","ico":"4","io3":"8","io38":"8","ipm10":"36","ipm2_5":"35","aqi":"36","primarypollutant":"aqi","quality":"优","timepoint":"2020-06-03 09:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}}
         * index : [{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"适宜","detail":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"iname":"紫外线指数","ivalue":"中等","detail":"属中等强度紫外线辐射天气，建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"},{"iname":"感冒指数","ivalue":"少发","detail":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},{"iname":"洗车指数","ivalue":"适宜","detail":"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。"},{"iname":"空气污染扩散指数","ivalue":"中","detail":"气象条件对空气污染物稀释、扩散和清除无明显影响。"},{"iname":"穿衣指数","ivalue":"热","detail":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}]
         * daily : [{"date":"2020-06-03","week":"星期三","sunrise":"05:47","sunset":"19:54","night":{"weather":"多云","templow":"17","img":"1","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"28","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-04","week":"星期四","sunrise":"05:47","sunset":"19:55","night":{"weather":"多云","templow":"19","img":"1","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"30","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-05","week":"星期五","sunrise":"05:47","sunset":"19:55","night":{"weather":"阴","templow":"21","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"晴","temphigh":"32","img":"0","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-06","week":"星期六","sunrise":"05:47","sunset":"19:56","night":{"weather":"阴","templow":"22","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"32","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-07","week":"星期日","sunrise":"05:46","sunset":"19:56","night":{"weather":"小雨","templow":"22","img":"7","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"30","img":"3","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-08","week":"星期一","sunrise":"05:46","sunset":"19:57","night":{"weather":"中雨","templow":"21","img":"8","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"29","img":"3","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-09","week":"星期二","sunrise":"05:46","sunset":"19:57","night":{"weather":"阵雨","templow":"22","img":"3","winddirect":"微风","windpower":"3级"},"day":{"weather":"阵雨","temphigh":"30","img":"3","winddirect":"微风","windpower":"3级"}}]
         * hourly : [{"time":"11:00","weather":"多云","temp":"23","img":"1"},{"time":"12:00","weather":"多云","temp":"25","img":"1"},{"time":"13:00","weather":"多云","temp":"26","img":"1"},{"time":"14:00","weather":"多云","temp":"27","img":"1"},{"time":"15:00","weather":"多云","temp":"28","img":"1"},{"time":"16:00","weather":"多云","temp":"28","img":"1"},{"time":"17:00","weather":"多云","temp":"28","img":"1"},{"time":"18:00","weather":"多云","temp":"26","img":"1"},{"time":"19:00","weather":"多云","temp":"25","img":"1"},{"time":"20:00","weather":"多云","temp":"23","img":"1"},{"time":"21:00","weather":"多云","temp":"22","img":"1"},{"time":"22:00","weather":"多云","temp":"21","img":"1"},{"time":"23:00","weather":"晴","temp":"20","img":"0"},{"time":"00:00","weather":"晴","temp":"20","img":"0"},{"time":"01:00","weather":"多云","temp":"19","img":"1"},{"time":"02:00","weather":"多云","temp":"19","img":"1"},{"time":"03:00","weather":"多云","temp":"18","img":"1"},{"time":"04:00","weather":"多云","temp":"18","img":"1"},{"time":"05:00","weather":"多云","temp":"17","img":"1"},{"time":"06:00","weather":"多云","temp":"18","img":"1"},{"time":"07:00","weather":"多云","temp":"20","img":"1"},{"time":"08:00","weather":"晴","temp":"21","img":"0"},{"time":"09:00","weather":"多云","temp":"22","img":"1"},{"time":"10:00","weather":"多云","temp":"24","img":"1"}]
         */

        private String city;
        private int cityid;
        private String citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private String img;
        private String humidity;
        private String pressure;
        private String windspeed;
        private String winddirect;
        private String windpower;
        private String updatetime;
        private AqiBean aqi;
        private List<IndexBean> index;
        private List<DailyBean> daily;
        private List<HourlyBean> hourly;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public static class AqiBean {
            /**
             * so2 : 5
             * so224 : 4
             * no2 : 14
             * no224 : 19
             * co : 0.400
             * co24 : 0.430
             * o3 : 25
             * o38 : 16
             * o324 : 18
             * pm10 : 36
             * pm1024 : 40
             * pm2_5 : 24
             * pm2_524 : 17
             * iso2 : 2
             * ino2 : 7
             * ico : 4
             * io3 : 8
             * io38 : 8
             * ipm10 : 36
             * ipm2_5 : 35
             * aqi : 36
             * primarypollutant : aqi
             * quality : 优
             * timepoint : 2020-06-03 09:00:00
             * aqiinfo : {"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}
             */

            private String so2;
            private String so224;
            private String no2;
            private String no224;
            private String co;
            private String co24;
            private String o3;
            private String o38;
            private String o324;
            private String pm10;
            private String pm1024;
            private String pm2_5;
            private String pm2_524;
            private String iso2;
            private String ino2;
            private String ico;
            private String io3;
            private String io38;
            private String ipm10;
            private String ipm2_5;
            private String aqi;
            private String primarypollutant;
            private String quality;
            private String timepoint;
            private AqiinfoBean aqiinfo;

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getSo224() {
                return so224;
            }

            public void setSo224(String so224) {
                this.so224 = so224;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getNo224() {
                return no224;
            }

            public void setNo224(String no224) {
                this.no224 = no224;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getCo24() {
                return co24;
            }

            public void setCo24(String co24) {
                this.co24 = co24;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getO38() {
                return o38;
            }

            public void setO38(String o38) {
                this.o38 = o38;
            }

            public String getO324() {
                return o324;
            }

            public void setO324(String o324) {
                this.o324 = o324;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm1024() {
                return pm1024;
            }

            public void setPm1024(String pm1024) {
                this.pm1024 = pm1024;
            }

            public String getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(String pm2_5) {
                this.pm2_5 = pm2_5;
            }

            public String getPm2_524() {
                return pm2_524;
            }

            public void setPm2_524(String pm2_524) {
                this.pm2_524 = pm2_524;
            }

            public String getIso2() {
                return iso2;
            }

            public void setIso2(String iso2) {
                this.iso2 = iso2;
            }

            public String getIno2() {
                return ino2;
            }

            public void setIno2(String ino2) {
                this.ino2 = ino2;
            }

            public String getIco() {
                return ico;
            }

            public void setIco(String ico) {
                this.ico = ico;
            }

            public String getIo3() {
                return io3;
            }

            public void setIo3(String io3) {
                this.io3 = io3;
            }

            public String getIo38() {
                return io38;
            }

            public void setIo38(String io38) {
                this.io38 = io38;
            }

            public String getIpm10() {
                return ipm10;
            }

            public void setIpm10(String ipm10) {
                this.ipm10 = ipm10;
            }

            public String getIpm2_5() {
                return ipm2_5;
            }

            public void setIpm2_5(String ipm2_5) {
                this.ipm2_5 = ipm2_5;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getPrimarypollutant() {
                return primarypollutant;
            }

            public void setPrimarypollutant(String primarypollutant) {
                this.primarypollutant = primarypollutant;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getTimepoint() {
                return timepoint;
            }

            public void setTimepoint(String timepoint) {
                this.timepoint = timepoint;
            }

            public AqiinfoBean getAqiinfo() {
                return aqiinfo;
            }

            public void setAqiinfo(AqiinfoBean aqiinfo) {
                this.aqiinfo = aqiinfo;
            }

            public static class AqiinfoBean {
                /**
                 * level : 一级
                 * color : #00e400
                 * affect : 空气质量令人满意，基本无空气污染
                 * measure : 各类人群可正常活动
                 */

                private String level;
                private String color;
                private String affect;
                private String measure;

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getAffect() {
                    return affect;
                }

                public void setAffect(String affect) {
                    this.affect = affect;
                }

                public String getMeasure() {
                    return measure;
                }

                public void setMeasure(String measure) {
                    this.measure = measure;
                }
            }
        }

        public static class IndexBean {
            /**
             * iname : 空调指数
             * ivalue : 较少开启
             * detail : 您将感到很舒适，一般不需要开启空调。
             */

            private String iname;
            private String ivalue;
            private String detail;

            public String getIname() {
                return iname;
            }

            public void setIname(String iname) {
                this.iname = iname;
            }

            public String getIvalue() {
                return ivalue;
            }

            public void setIvalue(String ivalue) {
                this.ivalue = ivalue;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }

        public static class DailyBean {
            /**
             * date : 2020-06-03
             * week : 星期三
             * sunrise : 05:47
             * sunset : 19:54
             * night : {"weather":"多云","templow":"17","img":"1","winddirect":"微风","windpower":"3级"}
             * day : {"weather":"多云","temphigh":"28","img":"1","winddirect":"微风","windpower":"3级"}
             */

            private String date;
            private String week;
            private String sunrise;
            private String sunset;
            private NightBean night;
            private DayBean day;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public NightBean getNight() {
                return night;
            }

            public void setNight(NightBean night) {
                this.night = night;
            }

            public DayBean getDay() {
                return day;
            }

            public void setDay(DayBean day) {
                this.day = day;
            }

            public static class NightBean {
                /**
                 * weather : 多云
                 * templow : 17
                 * img : 1
                 * winddirect : 微风
                 * windpower : 3级
                 */

                private String weather;
                private String templow;
                private String img;
                private String winddirect;
                private String windpower;

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemplow() {
                    return templow;
                }

                public void setTemplow(String templow) {
                    this.templow = templow;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getWinddirect() {
                    return winddirect;
                }

                public void setWinddirect(String winddirect) {
                    this.winddirect = winddirect;
                }

                public String getWindpower() {
                    return windpower;
                }

                public void setWindpower(String windpower) {
                    this.windpower = windpower;
                }
            }

            public static class DayBean {
                /**
                 * weather : 多云
                 * temphigh : 28
                 * img : 1
                 * winddirect : 微风
                 * windpower : 3级
                 */

                private String weather;
                private String temphigh;
                private String img;
                private String winddirect;
                private String windpower;

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemphigh() {
                    return temphigh;
                }

                public void setTemphigh(String temphigh) {
                    this.temphigh = temphigh;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getWinddirect() {
                    return winddirect;
                }

                public void setWinddirect(String winddirect) {
                    this.winddirect = winddirect;
                }

                public String getWindpower() {
                    return windpower;
                }

                public void setWindpower(String windpower) {
                    this.windpower = windpower;
                }
            }
        }

        public static class HourlyBean {
            /**
             * time : 11:00
             * weather : 多云
             * temp : 23
             * img : 1
             */

            private String time;
            private String weather;
            private String temp;
            private String img;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
