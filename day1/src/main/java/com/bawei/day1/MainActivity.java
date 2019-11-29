package com.bawei.day1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.day1.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private ViewPager viewPager;
    private RadioGroup radioGroup;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initData() {

        fragmentList.add(new HomeFragment());
        fragmentList.add(new OtherFragment());
        fragmentList.add(new MyFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_btua:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radio_btub:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radio_btuc:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        radioGroup.check(R.id.radio_btua);
                        break;
                    case 1:
                        radioGroup.check(R.id.radio_btub);
                        break;
                    case 2:
                        radioGroup.check(R.id.radio_btuc);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.radio_group);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    public void tiao(){
        viewPager.setCurrentItem(2);
    }
}
