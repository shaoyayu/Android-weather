package com.shaoyayu.data;

/**
 * Created by admin on 2018/11/24.
 */

public class Weather {

    private String date;
    private int typeImage;
    private String type;
    private String high;
    private String low;
    private String fx;
    private String fl;

    public Weather() {

    }

    public void setWeather(String date, int typeImage, String type, String high, String low) {
        this.date=date;
        this.typeImage = typeImage;
        this.type = type;
        this.high = high;
        this.low = low;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", typeImage=" + typeImage +
                ", type='" + type + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(int typeImage) {
        this.typeImage = typeImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
