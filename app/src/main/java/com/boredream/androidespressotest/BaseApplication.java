package com.boredream.androidespressotest;

import android.app.Application;

/**
 * Created by moyun on 2015/12/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BDVolley.init(this);
    }
}
