package com.acheronsheol.fragmentviewpagerdemo.base.proxy;


import com.acheronsheol.fragmentviewpagerdemo.base.view.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
