package com.lc.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.lc.core.utils.CrashUtils;
import com.lc.core.utils.SPUtils;
import com.lc.core.utils.Utils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by LiangCheng on 2017/8/21.
 */

public class BaseApp extends Application {

    private static BaseApp app;

    public static Context getAppContext() {
        return app;
    }

    public static Resources getAppResources() {
        return app.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this))
            return;

        //初始化内存泄露监测
        LeakCanary.install(this);
        app = this;
        //初始化app数据存储
        SPUtils.init(this);
        //Utils初始化
        Utils.init(this);
        //崩溃抓捕
        CrashUtils.getInstance().init();
    }
}
