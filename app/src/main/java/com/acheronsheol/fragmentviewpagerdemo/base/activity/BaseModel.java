package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import java.util.ArrayList;

public class BaseModel implements BaseModelInterface {

    private BasePresenterInterface basePresenter;

    public BaseModel(BasePresenterInterface basePresenter){
        this.basePresenter = basePresenter;
    }

    /*
     * 来自Socket长连接的任务
     * */
    @Override
    public void doBusinessEvent(int i) {

    }

    /*
     * 来自http的任务
     * */
    @Override
    public void doHttpEvent(int i) {

    }

}
