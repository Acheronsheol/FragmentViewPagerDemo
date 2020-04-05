package com.acheronsheol.fragmentviewpagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.acheronsheol.fragmentviewpagerdemo.base.activity.BaseActivity;
import com.acheronsheol.fragmentviewpagerdemo.base.activity.BasePresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.activity.BaseViewInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BaseViewInterface {

    BasePresenter basePresenter;

    @BindView(R.id.vp_tab_body)
    ViewPager vp_tab_body;

    @BindView(R.id.rg_tab_hub)
    RadioGroup rg_tab_hub;
    @BindView(R.id.rb_1)
    RadioButton rb_1;
    @BindView(R.id.rb_2)
    RadioButton rb_2;
    @BindView(R.id.rb_3)
    RadioButton rb_3;
    @BindView(R.id.rb_4)
    RadioButton rb_4;
    @BindView(R.id.rb_5)
    RadioButton rb_5;

    List<RadioButton> radioButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basePresenter = new BasePresenter(this);

        radioButtonList = new ArrayList<RadioButton>(){
            {
                add(rb_1);add(rb_2);add(rb_3);add(rb_4);add(rb_5);
            }
        };

        rg_tab_hub.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        rg_tab_hub.check(R.id.rb_1);
                        break;
                    case R.id.rb_2:
                        rg_tab_hub.check(R.id.rb_2);
                        break;
                    case R.id.rb_3:
                        rg_tab_hub.check(R.id.rb_3);
                        break;
                    case R.id.rb_4:
                        rg_tab_hub.check(R.id.rb_4);
                        break;
                    case R.id.rb_5:
                        rg_tab_hub.check(R.id.rb_5);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
