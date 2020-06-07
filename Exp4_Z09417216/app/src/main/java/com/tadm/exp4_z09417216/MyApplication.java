package com.tadm.exp4_z09417216;

import android.app.Application;

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
