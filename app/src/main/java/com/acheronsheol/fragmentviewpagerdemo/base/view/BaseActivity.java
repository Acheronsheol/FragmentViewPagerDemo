package com.acheronsheol.fragmentviewpagerdemo.base.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.acheronsheol.fragmentviewpagerdemo.base.proxy.ProxyActivity;



public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private static Toast shortToast;

    private static Toast longToast;

    private ProxyActivity mProxyActivity;

    protected abstract void initLayout();

    protected abstract void initViews();

    protected abstract void initData();


    @SuppressWarnings("SameParameterValue")
    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLayout();

        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();

        initViews();
        initData();
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
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
