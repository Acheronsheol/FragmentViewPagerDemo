package com.acheronsheol.fragmentviewpagerdemo.main.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.acheronsheol.fragmentviewpagerdemo.R;
import com.acheronsheol.fragmentviewpagerdemo.base.inject.InjectPresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.view.BaseFragment;

public class Test5Fragment extends BaseFragment implements TestContract.ITestView {

    @InjectPresenter
    private TestPresenter mPresenter;

    TextView tv_test;

    @Override
    protected int setLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        tv_test = $(R.id.tv_test);
    }

    @Override
    protected void initData() {
        tv_test.setText("第五页");
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void succes(String content) {

    }
}
