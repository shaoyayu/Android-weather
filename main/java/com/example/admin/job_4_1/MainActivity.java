package com.example.admin.job_4_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     *
     * 程序涉及基础：
     * 1.java的网络请求（get请求"http://t.weather.sojson.com/api/weather/city/"+weatherCode）
     * 2.Android的线程通讯
     * 3.xml的解析dom解析
     * 4.json的解析
     * 5.BaseAdapter适配器的使用（还没有优化getView的方法）
     *
     * 作者：邵涯语
     * 时间：2018年11月25日
     *
     */
    private Spinner spinner,spinner2,spinner3;
    private Button button;
    private ArrayAdapter<String> adapter,adapter2,adapter3;
    private List<String> list,list2,list3;
    static int position1,position2,position3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 初始化控件
         */
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        button = (Button) findViewById(R.id.button2);
        /**
         * 通过文件对于的ID创建一个输入流读取文件
         */
        final InputStream is;
        try {
            is = this.getResources().openRawResource(R.raw.china);
            list = ChinaXMLService.getRoot(is);
            //关闭流
            is.close();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        /**
         * 给spinner注册一个ArrayAdapter适配器
         * 给spinner注册一个选择事件监听器
         */
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position1 = position;
                Toast.makeText(MainActivity.this,"你的选择是:["+list.get(position1).toString()+"]",Toast.LENGTH_SHORT).show();
                /**
                 * list2是通过解析xml得到的数组
                 * 给spinner2注册一个ArrayAdapter适配器
                 * 给spinner2注册一个选择事件监听器
                 */
                list2=ChinaXMLService.getRootCity(list.get(position1).toString());
                adapter2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,list2);
                spinner2.setAdapter(adapter2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position2 = position;
                Toast.makeText(MainActivity.this,"你的选择是:["+list2.get(position2).toString()+"]",Toast.LENGTH_SHORT).show();
                /**
                 * list3是通过解析xml得到的数组
                 * 给spinner3注册一个ArrayAdapter适配器
                 * 给spinner3注册一个选择事件监听器
                 */
                list3 = ChinaXMLService.getCounty(list2.get(position2).toString());
                adapter3 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,list3);
                spinner3.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /**
         * 给spinner2注册一个选择事件监听器
         */
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position3 = position;
                Toast.makeText(MainActivity.this,"你的选择是:["+list3.get(position3).toString()+"]",Toast.LENGTH_SHORT).show();
                Log.i(list3.get(position3).toString(),ChinaXMLService.weatherCodeList.get(position3).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 给查找按钮注册点击事件处理
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                intent.putExtra("site",list.get(position1));
                intent.putExtra("weatherCode",ChinaXMLService.weatherCodeList.get(position3).toString());
                startActivity(intent);
            }
        });
    }


}
