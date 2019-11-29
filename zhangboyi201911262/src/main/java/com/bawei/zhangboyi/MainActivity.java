package com.bawei.zhangboyi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bawei.zhangboyi.base.BaseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *移动
 * 1707A
 * 张博一
 * 首页
 **/
public class MainActivity extends BaseActivity {


    private TabLayout tabLayout;
    private ImageView imageView;
    private ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();

    @Override
    protected void initData() {
        //标题
        //张博一初始化第一周周考项目代码
        stringList.add("要闻");
        stringList.add("推荐");
        stringList.add("视频");
        stringList.add("时事");
        stringList.add("财经");
        stringList.add("湃湃");

        //添加集合
        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);

        HomeFragment homeFragment1 = new HomeFragment();
        fragmentList.add(homeFragment1);

        HomeFragment homeFragment2 = new HomeFragment();
        fragmentList.add(homeFragment2);

        HomeFragment homeFragment3 = new HomeFragment();
        fragmentList.add(homeFragment3);

        HomeFragment homeFragment4 = new HomeFragment();
        fragmentList.add(homeFragment4);

        HomeFragment homeFragment5 = new HomeFragment();
        fragmentList.add(homeFragment5);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stringList.get(position);
            }
        });

        //关联
        tabLayout.setupWithViewPager(viewPager);

        //默认页面
        viewPager.setCurrentItem(1);

    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.imageview);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabl);
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
