package com.boredream.androidespressotest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(String s) {
        Log.i("DDD", "onEventBackgroundThread " + Thread.currentThread().getId() + "  " + s);
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    /**
     * 发起登录操作
     */
    private void login() {
        // 验证密码
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // 验证用户名
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

//        // 验证通过
//        requestLogin(username, password);

        // 登录成功,跳转到主页
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * 调用服务器登录接口(此处为模拟)
     */
    private void requestLogin(final String username, final String password) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                // 发起请求
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                // 请求ing... 模拟耗时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // 请求返回后
                progressDialog.dismiss();

                // 这里简单的判断下模拟服务器的账号处理
                if (!username.equals("boredream") || !password.equals("123456")) {
                    showToast("用户名或密码错误");
                    return;
                }

                // 登录成功,跳转到主页
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }.execute();
    }

}
