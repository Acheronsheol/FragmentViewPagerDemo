package com.acheronsheol.fragmentviewpagerdemo.main.avtivity;


import com.acheronsheol.fragmentviewpagerdemo.base.presenter.IBasePresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.view.IBaseView;

import okhttp3.Callback;

public interface MainContract {
    interface IMainModel {
        void requestBaidu(Callback callback);
    }

    interface IMainView extends IBaseView {
        void showDialog();

        void succes(String content);
    }

    interface IMainPresenter extends IBasePresenter {
        void handlerData();
    }
}