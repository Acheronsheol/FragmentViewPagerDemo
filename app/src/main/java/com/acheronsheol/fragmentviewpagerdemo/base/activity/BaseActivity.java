package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    private static Toast shortToast;
    private static Toast longToast;

    protected P mPresenter;

    protected abstract void initLayout();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract P setPresenter();

    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initLayout();

        /**
         * 实例化和绑定 P 层
         */
        mPresenter = setPresenter();
        mPresenter.bindPresenter(this);

        initViews();
        initData();
    }

    protected void onDestroy() {
        super.onDestroy();
        /**
         * 解绑，避免内存泄漏
         */
        mPresenter.unBindPresenter();
        mPresenter = null;
    }


    @Override
    public Context getContext() {
        return this;
    }

    public void onShortToast(String msg){
        try {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
                Toast shortToast;
                shortToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                shortToast.setGravity(Gravity.CENTER, 0, 0);
                shortToast.show();
            }
            else {
                if (shortToast == null) {
                    shortToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    shortToast.setGravity(Gravity.CENTER, 0, 0);
                } else {
                    shortToast.setText(msg);
                }
                shortToast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLongToast(String msg){
        try {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
                Toast longToast;
                longToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                longToast.setGravity(Gravity.CENTER, 0, 0);
                longToast.show();
            }
            else {
                if (longToast == null) {
                    longToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                    longToast.setGravity(Gravity.CENTER, 0, 0);
                } else {
                    longToast.setText(msg);
                }
                longToast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
