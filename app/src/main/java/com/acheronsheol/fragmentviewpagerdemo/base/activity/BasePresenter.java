package com.acheronsheol.fragmentviewpagerdemo.base.activity;

public class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V mView;

    @Override
    public void bindPresenter(IBaseView view) {
        mView = (V) view;
    }

    @Override
    public void unBindPresenter() {
        mView = null;
    }



    /*
     * 用户要进行的事件
     * */

    /*
     * 用户事件的结果 或 Socket长连接&Http想要发生的事件(获取数据或是行为)
     * */

}
