package com.acheronsheol.fragmentviewpagerdemo.main.fragment;

import com.acheronsheol.fragmentviewpagerdemo.base.presenter.BasePresenter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TestPresenter extends BasePresenter<TestContract.ITestView,TestDataModel> implements TestContract.ITestPresenter {

    @Override
    public void handlerData() {
        getView().showDialog();

        getModel().requestBaidu(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                getView().succes(content);
            }
        });
    }
}
