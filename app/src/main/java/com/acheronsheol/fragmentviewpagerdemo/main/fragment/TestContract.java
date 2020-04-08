package com.acheronsheol.fragmentviewpagerdemo.main.fragment;


import com.acheronsheol.fragmentviewpagerdemo.base.presenter.IBasePresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.view.IBaseView;

import okhttp3.Callback;

public interface TestContract {

    interface ITestModel {
        void requestBaidu(Callback callback);
    }

    interface ITestView extends IBaseView {

        void showDialog();

        void succes(String content);
    }

    interface ITestPresenter extends IBasePresenter {
        void handlerData();
    }

}