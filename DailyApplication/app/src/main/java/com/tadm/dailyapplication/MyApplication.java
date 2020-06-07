/*
 * https://liuhongwei3.github.io Inc.
 * Name: MyApplication.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication;

import org.litepal.LitePalApplication;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MyApplication extends LitePalApplication {
    public void onCreate() {
        super.onCreate();
        SQLiteStudioService.instance().start(this);
    }

    public void onTerminate() {
        super.onTerminate();
        SQLiteStudioService.instance().stop();
    }
}
