package com.boredream.androidespressotest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("登录中...");

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

// ---------------------------    登录测试用例   ---------------------------
//
//        编号      输入/动作                            期望的输出/相应
//        1         使用合法用户名和密码登陆              登陆成功,进入主页
//        2         使用错误的用户名或密码登陆            显示用户名或密码错误提示信息
//        3         用户名为空登陆                       显示请输入用户名提示信息
//        4         密码为空进行登陆                      显示请输入密码提示信息
//        5         用户名和密码都为空进行登陆            显示请输入用户名提示信息(由上到下以依次判断)

    /**
     * 发起登录操作
     */
    private void login() {
        // 验证密码
        String password = et_password.getText().toString().trim();
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // 验证用户名
        String username = et_username.getText().toString().trim();
        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        // 验证通过
        requestLogin(username, password);
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
                // 请求ing...
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

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
