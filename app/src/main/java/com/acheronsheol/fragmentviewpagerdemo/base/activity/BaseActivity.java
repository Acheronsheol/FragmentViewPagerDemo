package com.acheronsheol.fragmentviewpagerdemo.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


public abstract class BaseActivity<I> extends AppCompatActivity{

    private static Toast shortToast;
    private static Toast longToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
