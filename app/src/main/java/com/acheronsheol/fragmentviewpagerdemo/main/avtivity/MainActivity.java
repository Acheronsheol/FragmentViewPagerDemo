package com.acheronsheol.fragmentviewpagerdemo.main.avtivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.acheronsheol.fragmentviewpagerdemo.R;
import com.acheronsheol.fragmentviewpagerdemo.base.view.BaseActivity;
import com.acheronsheol.fragmentviewpagerdemo.base.inject.InjectPresenter;

public class MainActivity extends BaseActivity implements MainContract.IMainView {

    RadioGroup rg_tab_hub;

    @InjectPresenter
    private MainPresenter mPresenter;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        rg_tab_hub = $(R.id.rg_tab_hub);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void succes(String content) {

    }
}
