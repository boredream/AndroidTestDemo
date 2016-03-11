package com.boredream.androidespressotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.boredream.androidespressotest.R;

public class TestFragment extends Fragment implements View.OnClickListener {


    private EditText et_username;
    private TextView tv_regist;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_login, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_username = (EditText) view.findViewById(R.id.et_username);
        tv_regist = (TextView) view.findViewById(R.id.tv_regist);

        tv_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_regist:

                break;
        }
    }

    private void submit() {
        // validate
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
