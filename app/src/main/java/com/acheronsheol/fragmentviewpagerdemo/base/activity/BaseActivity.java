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

import com.acheronsheol.fragmentviewpagerdemo.base.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private static Toast shortToast;
    private static Toast longToast;

    /**
     * 保存使用注解的 Presenter ，用于解绑
     */
    private List<BasePresenter> mInjectPresenters;

    protected abstract void initLayout();

    protected abstract void initViews();

    protected abstract void initData();

    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initLayout();

        mInjectPresenters = new ArrayList<>();

        //获得已经申明的变量，包括私有的
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取变量上面的注解类型
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                try {
                    Class<? extends BasePresenter> type = (Class<? extends BasePresenter>) field.getType();
                    BasePresenter mInjectPresenter = type.newInstance();
                    mInjectPresenter.bindPresenter(this);
                    field.setAccessible(true);
                    field.set(this, mInjectPresenter);
                    mInjectPresenters.add(mInjectPresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }catch (ClassCastException e){
                    e.printStackTrace();
                    throw new RuntimeException("SubClass must extends Class:BasePresenter");
                }
            }
        }

        initViews();
        initData();
    }

    protected void onDestroy() {
        super.onDestroy();
        /**
         * 解绑，避免内存泄漏
         */
        for (BasePresenter presenter : mInjectPresenters) {
            presenter.unBindPresenter();
        }
        mInjectPresenters.clear();
        mInjectPresenters = null;
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
