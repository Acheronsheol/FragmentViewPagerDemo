package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import android.content.Context;

public interface IBaseView {

    Context getContext();

    void onShortToast(String msg);

    void onLongToast(String msg);

}
