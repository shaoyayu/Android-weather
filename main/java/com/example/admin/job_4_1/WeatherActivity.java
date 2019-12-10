package com.example.admin.job_4_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaoyayu.data.PictureMappingData;
import com.shaoyayu.data.Weather;
import com.shaoyayu.data.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements PictureMappingData {

    private ListView listView;
    private TextView tv_province,tv_region,tv_data1,tv_week1,tv_weather1,tv_tempHige,tv_tempLow,tv_notice1;
    private ImageView imageView;

    /**
     * @String weatherCode
     * 城市对于的weatherCode值，
     * @String site
     * 城市的省或直辖市名称如"湖北"，"北京"
     * @WeatherForecast weatherForecast
     * 通过json解析的到的对象封装数据
     */
    private String weatherCode;
    private String site;
    private String jsonString;
    private WeatherForecast weatherForecast = new WeatherForecast();
    private List<Weather> listWeather;
    /**
     * 线程监听处理
     */
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x01){
                Toast.makeText(WeatherActivity.this,"数据解析完成，正在加载...",Toast.LENGTH_SHORT).show();
                initData();
            }
        }
    };

    /**
     * Activity的onCreate方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //接收MainActivity传送过来的数据，site、weatherCode
        Intent intent = getIntent();
        site = intent.getStringExtra("site");
        Log.i("..........",site.toString());
        weatherCode = intent.getStringExtra("weatherCode");
        //初始化控件
        init();
        //在子线程中完成数据初始化操作和数据展示
        this.getJson(null);
    }
    /**
     * 初始化控件的方法
     */
    public void init(){
        imageView = (ImageView) findViewById(R.id.imageView);
        tv_province = (TextView) findViewById(R.id.textView2);
        tv_region = (TextView) findViewById(R.id.textView4);
        tv_data1 = (TextView) findViewById(R.id.textView3);
        tv_week1 = (TextView) findViewById(R.id.textView5);
        tv_weather1 = (TextView) findViewById(R.id.textView6);
        tv_tempHige = (TextView) findViewById(R.id.textView8);
        tv_tempLow = (TextView) findViewById(R.id.textView9);
        tv_notice1 = (TextView) findViewById(R.id.textView12);
        listView = (ListView) findViewById(R.id.list_view);

    }

    /**
     * 初始化数据展示，
     */
    public void initData(){
        //
        tv_province.setText(site+"/"+weatherForecast.getCityInfo().getParent());
        tv_region.setText(weatherForecast.getCityInfo().getCity());
        tv_data1.setText(weatherForecast.getTime());
        tv_week1.setText(weatherForecast.getData().getForecast().get(0).getDate());
        tv_weather1.setText(weatherForecast.getData().getForecast().get(0).getType());
        imageView.setImageResource(stringToImageID(weatherForecast.getData().getForecast().get(0).getType()));
        tv_tempHige.setText(weatherForecast.getData().getForecast().get(0).getHigh());
        tv_tempLow.setText(weatherForecast.getData().getForecast().get(0).getLow());
        tv_notice1.setText(weatherForecast.getData().getForecast().get(0).getNotice());
        listWeather = new ArrayList<Weather>();
        listWeather = this.getListWeather();
        //给listView注册适配器
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);

    }
    /**
     *将天气对于的类型找到相符的图片
     * @param string
     * @return
     */
    public int stringToImageID(String string){
        for (int i=0; i<PictureMappingData.datas.length; i++){
            if (string.equals(PictureMappingData.datas[i])){
                return PictureMappingData.imangsData[i];
            }
        }
        return 0;
    }
    /**
     * 开启子线程进行网络请求天气的json数据
     * @param source
     */
    public void getJson(View source){
        new Thread(){
            public void run(){
                String past="http://t.weather.sojson.com/api/weather/city/"+weatherCode;
                try {
                    URL url = new URL(past);
                    try {
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                        String string;
                        jsonString="";
                        while ((string=reader.readLine())!=null){
                            jsonString+=string;
                        }
                        Log.i("==============","==================");
                        Log.i("jsonString:",jsonString);
                        JsonToObjct(jsonString);
                        handler.sendEmptyMessage(0x01);

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private List<Weather> getListWeather(){
        List<Weather> list = new ArrayList<>();
        Weather weather = new Weather();
        weather.setWeather(weatherForecast.getData().getYesterday().getDate(),
                stringToImageID(weatherForecast.getData().getYesterday().getType()),
                weatherForecast.getData().getYesterday().getType(),
                weatherForecast.getData().getYesterday().getHigh(),
                weatherForecast.getData().getYesterday().getLow());
        Log.i("---------","--------");
        Log.i("......",weather.toString());
        list.add(weather);
        for (int i = 0; i<weatherForecast.getData().getForecast().size(); i++){
            Weather weather1 = new Weather();
            weather1.setWeather(
                    weatherForecast.getData().getForecast().get(i).getDate(),
                    stringToImageID(weatherForecast.getData().getForecast().get(i).getType()),
                    weatherForecast.getData().getForecast().get(i).getType(),
                    weatherForecast.getData().getForecast().get(i).getHigh(),
                    weatherForecast.getData().getForecast().get(i).getLow());
            Log.i("---------","--------");
            Log.i(i+"......",weather1.toString());
            list.add(weather1);
        }
        return list;
    }
    /**
     * 在子线程中完成
     * 解析json字符串，并封装到WeatherForecast对象中，
     *
     * @param jsonString
     */
    public void JsonToObjct(String jsonString){
        try {
            JSONObject jsonObject1 = new JSONObject(jsonString);
            //第一层解析
            /**
             * 解析WeatherForecast对象
             */
            String time = jsonObject1.optString("time");
            String date = jsonObject1.optString("date");
            String message = jsonObject1.optString("message");
            int status = jsonObject1.optInt("status");
            JSONObject cityInfo = jsonObject1.optJSONObject("cityInfo");
            JSONObject data = jsonObject1.optJSONObject("data");
            //第一层数据封装
            weatherForecast.setTime(time);
            weatherForecast.setDate(date);
            weatherForecast.setMessage(message);
            weatherForecast.setStatus(status);
            WeatherForecast.CityInfoBean cityInfoBean = new WeatherForecast.CityInfoBean();
            weatherForecast.setCityInfo(cityInfoBean);
            WeatherForecast.DataBean dataBean = new WeatherForecast.DataBean();
            weatherForecast.setData(dataBean);


            //第二层解析
            /**
             * 解析CityInfoBean对象
             */
            String city = cityInfo.optString("city");
            String cityId = cityInfo.optString("cityId");
            String parent = cityInfo.optString("parent");
            String updateTime = cityInfo.optString("updateTime");
            //第二层CityInfoBean对象封装
            cityInfoBean.setCity(city);
            cityInfoBean.setCityId(cityId);
            cityInfoBean.setParent(parent);
            cityInfoBean.setUpdateTime(updateTime);

            /**
             * 解析DataBean对象
             */
            String shidu = data.optString("shidu");
            double pm25 = data.optDouble("pm25");
            double pm10 = data.optDouble("pm10");
            String quality = data.optString("quality");
            String wendu = data.optString("wendu");
            String ganmao = data.optString("ganmao");
            JSONObject yesterday = data.optJSONObject("yesterday");
            JSONArray forecast = data.optJSONArray("forecast");
            //
            dataBean.setShidu(shidu);
            dataBean.setPm25(pm25);
            dataBean.setPm10(pm10);
            dataBean.setQuality(quality);
            dataBean.setWendu(wendu);
            dataBean.setGanmao(ganmao);
            WeatherForecast.DataBean.YesterdayBean yesterdayBean = new WeatherForecast.DataBean.YesterdayBean();
            dataBean.setYesterday(yesterdayBean);
            List<WeatherForecast.DataBean.ForecastBean> forecastBeanList = new ArrayList<WeatherForecast.DataBean.ForecastBean>();
            dataBean.setForecast(forecastBeanList);


            //第三层解析
            /**
             * 解析YesterdayBean
             */
            String date1 = yesterday.optString("date");
            String sunrise = yesterday.optString("sunrise");
            String high = yesterday.optString("high");
            String low = yesterday.optString("low");
            String sunset = yesterday.optString("sunset");
            double aqi = yesterday.optDouble("aqi");
            String fx = yesterday.optString("fx");
            String fl = yesterday.optString("fl");
            String type = yesterday.optString("type");
            String notice = yesterday.optString("notice");
            //
            yesterdayBean.setDate(date1);
            yesterdayBean.setSunrise(sunrise);
            yesterdayBean.setHigh(high);
            yesterdayBean.setLow(low);
            yesterdayBean.setSunset(sunset);
            yesterdayBean.setAqi(aqi);
            yesterdayBean.setFx(fx);
            yesterdayBean.setFl(fl);
            yesterdayBean.setType(type);
            yesterdayBean.setNotice(notice);

            /**
             * 解析List<ForecastBean>
             */

            for (int i=0; i< forecast.length(); i++){
                JSONObject forecastBean = forecast.optJSONObject(i);
                if (forecastBean != null){
                    String date2 = forecastBean.optString("date");
                    String sunrise2 = forecastBean.optString("sunrise");
                    String high2 = forecastBean.optString("high");
                    String low2 = forecastBean.optString("low");
                    String sunset2 = forecastBean.optString("sunset");
                    double aqi2 = forecastBean.optDouble("api");
                    String fx2 = forecastBean.optString("fx");
                    String fl2 = forecastBean.optString("fl");
                    String type2 = forecastBean.optString("type");
                    String notice2 = forecastBean.optString("notice");

                    WeatherForecast.DataBean.ForecastBean forecast_Bean = new WeatherForecast.DataBean.ForecastBean();

                    forecast_Bean.setDate(date2);
                    forecast_Bean.setSunrise(sunrise2);
                    forecast_Bean.setHigh(high2);
                    forecast_Bean.setLow(low2);
                    forecast_Bean.setSunset(sunset2);
                    forecast_Bean.setAqi(aqi2);
                    forecast_Bean.setFx(fx2);
                    forecast_Bean.setFl(fl2);
                    forecast_Bean.setType(type2);
                    forecast_Bean.setNotice(notice2);
                    forecastBeanList.add(forecast_Bean);
                }else {
                    Log.i("=================","================");
                    Log.i("=================","forecastBean为空");
                    Log.i("=================","================");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listWeather.size();
        }

        @Override
        public Object getItem(int position) {

            return position;

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(WeatherActivity.this);
            View view = inflater.inflate(R.layout.weather_list_view, parent, false);
            Weather weather = listWeather.get(position);

            TextView tv_date3 = (TextView) view.findViewById(R.id.textView10);
            tv_date3.setText(weather.getDate());

            ImageView iv_type3 = (ImageView) view.findViewById(R.id.imageView3);
            iv_type3.setImageResource(weather.getTypeImage());
            TextView tv_type3 = (TextView) view.findViewById(R.id.textView13);
            tv_type3.setText(weather.getType());
            TextView tv_high3 = (TextView) view.findViewById(R.id.textView15);
            tv_high3.setText(weather.getHigh());
            TextView tv_low3 = (TextView) view.findViewById(R.id.textView16);
            tv_low3.setText(weather.getLow());
            return view;
        }
    }
}
