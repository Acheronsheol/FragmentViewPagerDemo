package com.acheronsheol.fragmentviewpagerdemo.base.proxy;

import com.acheronsheol.fragmentviewpagerdemo.base.view.IBaseView;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}