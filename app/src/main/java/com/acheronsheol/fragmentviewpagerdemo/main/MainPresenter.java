package com.acheronsheol.fragmentviewpagerdemo.main;

import com.acheronsheol.fragmentviewpagerdemo.base.activity.BasePresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.activity.IBaseView;

public class MainPresenter extends BasePresenter<MainContract.IMainView,DataModel> implements MainContract.IMainPresenter {

    private MainContract.IMainModel mModel;
    private MainContract.IMainView mView;

    public MainPresenter(MainContract.IMainView view) {
        this.mView = view;
        this.mModel = new DataModel();
    }

    @Override
    public void bindPresenter(IBaseView view) {
        super.bindPresenter(view);
    }

    @Override
    public void unBindPresenter() {
        super.unBindPresenter();
    }

}
