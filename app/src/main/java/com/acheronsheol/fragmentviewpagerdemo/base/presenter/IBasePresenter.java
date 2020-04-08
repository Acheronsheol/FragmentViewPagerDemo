package com.acheronsheol.fragmentviewpagerdemo.base.presenter;

import com.acheronsheol.fragmentviewpagerdemo.base.view.IBaseView;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();

}