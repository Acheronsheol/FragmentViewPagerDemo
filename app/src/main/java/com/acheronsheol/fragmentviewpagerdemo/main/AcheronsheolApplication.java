package com.acheronsheol.fragmentviewpagerdemo.main;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.bun.miitmdid.core.JLibrary;

public class AcheronsheolApplication extends MultiDexApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        try {
            JLibrary.InitEntry(this);//初始化oaid获取
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getAppContext(){
        return mContext;
    }

}
