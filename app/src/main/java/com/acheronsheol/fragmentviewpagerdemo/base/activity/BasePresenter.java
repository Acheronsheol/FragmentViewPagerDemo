package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import java.util.ArrayList;

public class BasePresenter implements BasePresenterInterface {

    private BaseViewInterface baseView;
    private BaseModelInterface baseModel;

    public BasePresenter(BaseViewInterface baseView){//activity的view实例
        this.baseView = baseView;
        this.baseModel = new BaseModel(this);
    }

    /*
     * 用户要进行的事件
     * */

    /*
     * 用户事件的结果 或 Socket长连接&Http想要发生的事件(获取数据或是行为)
     * */

}
