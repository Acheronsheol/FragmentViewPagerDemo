package com.acheronsheol.fragmentviewpagerdemo.main.avtivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.acheronsheol.fragmentviewpagerdemo.R;
import com.acheronsheol.fragmentviewpagerdemo.base.view.BaseActivity;
import com.acheronsheol.fragmentviewpagerdemo.base.inject.InjectPresenter;
import com.acheronsheol.fragmentviewpagerdemo.base.view.BaseFragment;
import com.acheronsheol.fragmentviewpagerdemo.main.fragment.Test1Fragment;
import com.acheronsheol.fragmentviewpagerdemo.main.fragment.Test2Fragment;
import com.acheronsheol.fragmentviewpagerdemo.main.fragment.Test3Fragment;
import com.acheronsheol.fragmentviewpagerdemo.main.fragment.Test4Fragment;
import com.acheronsheol.fragmentviewpagerdemo.main.fragment.Test5Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.IMainView {


    List<BaseFragment> fragment_list;
//    BaseAdapter baseAdapter;

    FrameLayout fl_tab_body;
    RadioGroup rg_tab_hub;

    @InjectPresenter
    private MainPresenter mPresenter;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        fl_tab_body = $(R.id.fl_tab_body);
        rg_tab_hub = $(R.id.rg_tab_hub);
    }


    @Override
    protected void initData() {
        fragment_list = new ArrayList<BaseFragment>() {{for(int i=0;i<5;i++){ add(null);}}};
//        baseAdapter = new BaseAdapter(getSupportFragmentManager(),fragment_list);
        //默认选中第一个tab
        showFragment(0);
        rg_tab_hub.check(R.id.rb_1);
        rg_tab_hub.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_1:
                        showFragment(0);
                        break;
                    case R.id.rb_2:
                        showFragment(1);
                        break;
                    case R.id.rb_3:
                        showFragment(2);
                        break;
                    case R.id.rb_4:
                        showFragment(3);
                        break;
                    case R.id.rb_5:
                        showFragment(4);
                        break;
                }
            }
        });

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

    private void showFragment(int page) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);

        switch (page) {
            case 0:
                // 如果fragment1已经存在则将其显示出来
                if (fragment_list.get(0) != null)
                    ft.show(fragment_list.get(0));
                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    fragment_list.set(0,new Test1Fragment());
                    ft.add(R.id.fl_tab_body, fragment_list.get(0));
                }
                break;
            case 1:
                if (fragment_list.get(1) != null)
                    ft.show(fragment_list.get(1));
                else {
                    fragment_list.set(1,new Test2Fragment());
                    ft.add(R.id.fl_tab_body, fragment_list.get(1));
                }
                break;
            case 2:
                if (fragment_list.get(2) != null) {
                    ft.show(fragment_list.get(2));
                }
                else {
                    fragment_list.set(2,new Test3Fragment());
                    ft.add(R.id.fl_tab_body, fragment_list.get(2));
                }
                break;
            case 3:
                if (fragment_list.get(3) != null){
                    ft.show(fragment_list.get(3));
                }
                else {
                    fragment_list.set(3,new Test4Fragment());
                    ft.add(R.id.fl_tab_body, fragment_list.get(3));
                }
                break;
            case 4:
                if (fragment_list.get(4) != null){
                    ft.show(fragment_list.get(4));
                }
                else {
                    fragment_list.set(4,new Test5Fragment());
                    ft.add(R.id.fl_tab_body, fragment_list.get(4));
                }
                break;
        }
        ft.commit();
    }

    // 当fragment已被实例化，相当于发生过切换，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (fragment_list.get(0) != null)
            ft.hide(fragment_list.get(0));
        if (fragment_list.get(1) != null)
            ft.hide(fragment_list.get(1));
        if (fragment_list.get(2) != null)
            ft.hide(fragment_list.get(2));
        if (fragment_list.get(3) != null)
            ft.hide(fragment_list.get(3));
        if (fragment_list.get(4) != null)
            ft.hide(fragment_list.get(4));
    }


    public class BaseAdapter extends FragmentPagerAdapter {

        List<BaseFragment> mFragmentList;

        public BaseAdapter(FragmentManager fm ,List<BaseFragment> mFragmentList) {
            super(fm);
            this.mFragmentList = mFragmentList;
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size() > 0 ? mFragmentList.size() : 0;
        }
    }


}
