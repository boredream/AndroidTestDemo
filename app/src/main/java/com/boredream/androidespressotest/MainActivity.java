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

    private void getWeather(String citypinyin) {
        progressDialog.show();

        RequestQueue requestQueue = BDVolley.getRequestQueue();
        StringRequest sRequest = new StringRequest("http://apistore.baidu.com/microservice/weather?citypinyin=" + citypinyin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        WeatherResponse weatherResponse = new Gson().fromJson(response, WeatherResponse.class);
                        if(weatherResponse.getErrNum() == 0) {
                            tv_weather.setText("温度: " + weatherResponse.getRetData().getTemp());
                        } else {
                            tv_weather.setText("获取失败");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        showToast("获取失败");
                    }
                });
        requestQueue.add(sRequest);
    }

}
