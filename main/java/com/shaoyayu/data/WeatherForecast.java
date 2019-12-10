package com.shaoyayu.data;

import java.util.List;
/**
 * 封装json对象
 * Created by admin on 2018/11/20.
 */

public class WeatherForecast {

    /**
     * time : 2018-11-20 19:06:57
     * cityInfo : {"city":"重庆市","cityId":"101040100","parent":"重庆","updateTime":"18:52"}
     * date : 20181120
     * message : Success !
     * status : 200
     * data : {"shidu":"78%","pm25":51,"pm10":85,"quality":"良","wendu":"10","ganmao":"极少数敏感人群应减少户外活动","yesterday":
     * {"date":"19日星期一","sunrise":"07:20","high":"高温 12.0℃","low":"低温 9.0℃","sunset":"17:57","aqi":39,"fx":"无持续风向","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},"forecast":[
     * {"date":"20日星期二","sunrise":"07:21","high":"高温 14.0℃","low":"低温 9.0℃","sunset":"17:57","aqi":54,"fx":"无持续风向","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},
     * {"date":"21日星期三","sunrise":"07:22","high":"高温 10.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":72,"fx":"无持续风向","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},
     * {"date":"22日星期四","sunrise":"07:22","high":"高温 15.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":51,"fx":"无持续风向","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},
     * {"date":"23日星期五","sunrise":"07:23","high":"高温 18.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":71,"fx":"无持续风向","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},
     * {"date":"24日星期六","sunrise":"07:24","high":"高温 12.0℃","low":"低温 10.0℃","sunset":"17:56","aqi":111,"fx":"无持续风向","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"}]}
     */

    private String time;
    private CityInfoBean cityInfo;
    private String date;
    private String message;
    private int status;
    private DataBean data;

    public WeatherForecast() {
    }

    public WeatherForecast(String time, CityInfoBean cityInfo, String date, String message, int status, DataBean data) {
        this.time = time;
        this.cityInfo = cityInfo;
        this.date = date;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "time='" + time + '\'' +
                ", cityInfo=" + cityInfo +
                ", date='" + date + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfoBean getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoBean cityInfo) {
        this.cityInfo = cityInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class CityInfoBean {
        /**
         * city : 重庆市
         * cityId : 101040100
         * parent : 重庆
         * updateTime : 18:52
         */

        private String city;
        private String cityId;
        private String parent;
        private String updateTime;

        public CityInfoBean() {
        }

        public CityInfoBean(String city, String cityId, String parent, String updateTime) {
            this.city = city;
            this.updateTime = updateTime;
            this.cityId = cityId;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "CityInfoBean{" +
                    "city='" + city + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", parent='" + parent + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    '}';
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class DataBean {
        /**
         * shidu : 78%
         * pm25 : 51.0
         * pm10 : 85.0
         * quality : 良
         * wendu : 10
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"19日星期一","sunrise":"07:20","high":"高温 12.0℃","low":"低温 9.0℃","sunset":"17:57","aqi":39,"fx":"无持续风向","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"}
         * forecast : [{"date":"20日星期二","sunrise":"07:21","high":"高温 14.0℃","low":"低温 9.0℃","sunset":"17:57","aqi":54,"fx":"无持续风向","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},
         * {"date":"21日星期三","sunrise":"07:22","high":"高温 10.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":72,"fx":"无持续风向","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},
         * {"date":"22日星期四","sunrise":"07:22","high":"高温 15.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":51,"fx":"无持续风向","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},
         * {"date":"23日星期五","sunrise":"07:23","high":"高温 18.0℃","low":"低温 8.0℃","sunset":"17:56","aqi":71,"fx":"无持续风向","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},
         * {"date":"24日星期六","sunrise":"07:24","high":"高温 12.0℃","low":"低温 10.0℃","sunset":"17:56","aqi":111,"fx":"无持续风向","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"}]
         */

        private String shidu;
        private double pm25;
        private double pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public DataBean() {
        }

        public DataBean(String shidu, double pm25,  double pm10, String quality, String wendu,  String ganmao,YesterdayBean yesterday, List<ForecastBean> forecast) {
            this.shidu = shidu;
            this.pm25 = pm25;
            this.quality = quality;
            this.pm10 = pm10;
            this.wendu = wendu;
            this.yesterday = yesterday;
            this.ganmao = ganmao;
            this.forecast = forecast;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "shidu='" + shidu + '\'' +
                    ", pm25=" + pm25 +
                    ", pm10=" + pm10 +
                    ", quality='" + quality + '\'' +
                    ", wendu='" + wendu + '\'' +
                    ", ganmao='" + ganmao + '\'' +
                    ", yesterday=" + yesterday +
                    ", forecast=" + forecast +
                    '}';
        }

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public double getPm25() {
            return pm25;
        }

        public void setPm25(double pm25) {
            this.pm25 = pm25;
        }

        public double getPm10() {
            return pm10;
        }

        public void setPm10(double pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 19日星期一
             * sunrise : 07:20
             * high : 高温 12.0℃
             * low : 低温 9.0℃
             * sunset : 17:57
             * aqi : 39.0
             * fx : 无持续风向
             * fl : <3级
             * type : 阴
             * notice : 不要被阴云遮挡住好心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public YesterdayBean() {
            }

            public YesterdayBean(String date, String sunrise, String high, String low, String sunset,  double aqi, String fx,String fl, String type, String notice) {
                this.sunrise = sunrise;
                this.date = date;
                this.high = high;
                this.low = low;
                this.fx = fx;
                this.aqi = aqi;
                this.sunset = sunset;
                this.fl = fl;
                this.type = type;
                this.notice = notice;
            }

            @Override
            public String toString() {
                return "YesterdayBean{" +
                        "date='" + date + '\'' +
                        ", sunrise='" + sunrise + '\'' +
                        ", high='" + high + '\'' +
                        ", low='" + low + '\'' +
                        ", sunset='" + sunset + '\'' +
                        ", aqi=" + aqi +
                        ", fx='" + fx + '\'' +
                        ", fl='" + fl + '\'' +
                        ", type='" + type + '\'' +
                        ", notice='" + notice + '\'' +
                        '}';
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 20日星期二
             * sunrise : 07:21
             * high : 高温 14.0℃
             * low : 低温 9.0℃
             * sunset : 17:57
             * aqi : 54.0
             * fx : 无持续风向
             * fl : <3级
             * type : 阴
             * notice : 不要被阴云遮挡住好心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private double aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public ForecastBean() {
            }

            public ForecastBean(String date, String sunrise,String high, String low, String sunset,  double aqi, String fx, String fl, String type, String notice) {
                this.high = high;
                this.sunrise = sunrise;
                this.date = date;
                this.sunset = sunset;
                this.low = low;
                this.aqi = aqi;
                this.fx = fx;
                this.type = type;
                this.fl = fl;
                this.notice = notice;
            }

            @Override
            public String toString() {
                return "ForecastBean{" +
                        "date='" + date + '\'' +
                        ", sunrise='" + sunrise + '\'' +
                        ", high='" + high + '\'' +
                        ", low='" + low + '\'' +
                        ", sunset='" + sunset + '\'' +
                        ", aqi=" + aqi +
                        ", fx='" + fx + '\'' +
                        ", fl='" + fl + '\'' +
                        ", type='" + type + '\'' +
                        ", notice='" + notice + '\'' +
                        '}';
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public double getAqi() {
                return aqi;
            }

            public void setAqi(double aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
