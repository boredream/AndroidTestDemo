package com.boredream.androidespressotest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity {

    private EditText et_citypinyin;
    private TextView tv_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_citypinyin = (EditText) findViewById(R.id.et_citypinyin);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        findViewById(R.id.btn_get_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String citypinyin = et_citypinyin.getText().toString();
                if (TextUtils.isEmpty(citypinyin)) {
                    tv_weather.setText("获取失败");
                    return;
                }

                getWeather(citypinyin);
            }
        });
    }

    /**
     * 请求获取天气,接口是api商店里找的 http://apistore.baidu.com/astore/serviceinfo/1798.html
     */
    private void getWeather(String citypinyin) {
        progressDialog.show();

        RequestQueue requestQueue = BDVolley.getRequestQueue();
        StringRequest sRequest = new StringRequest("http://apistore.baidu.com/microservice/weather?citypinyin=" + citypinyin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //　模拟耗时
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();

                        // Gson解析数据后判断使用
                        BaseResponse br = new Gson().fromJson(response, BaseResponse.class);
                        if(br.getErrNum() == 0) {
                            // 比如输入错误城市时,这个破接口retData是个空集合[],直接解析会报错
                            // 所以先解析为BaseResponse判断,成功后再解析天气数据
                            WeatherResponse weather = new Gson().fromJson(response, WeatherResponse.class);
                            tv_weather.setText("温度: " + weather.getRetData().getTemp());
                        } else {
                            tv_weather.setText("获取失败," + br.getErrMsg());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                        showToast("获取失败");
                    }
                });
        requestQueue.add(sRequest);
    }

}
