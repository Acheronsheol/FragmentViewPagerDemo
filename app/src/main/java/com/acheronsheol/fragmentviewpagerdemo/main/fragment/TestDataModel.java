package com.acheronsheol.fragmentviewpagerdemo.main.fragment;

import com.acheronsheol.fragmentviewpagerdemo.base.model.BaseModel;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TestDataModel extends BaseModel implements TestContract.ITestModel {
    @Override
    public void requestBaidu(Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://blog.csdn.net/smile_running")
                .build();
        client.newCall(request).enqueue(callback);
    }
}
