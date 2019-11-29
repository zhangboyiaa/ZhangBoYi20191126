package com.bawei.day1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.bawei.day1.base.BaseFragment;

/**
 * date:2019/11/29
 * author:张博一(zhangboyi)
 * function:
 */
public class OtherFragment extends BaseFragment {

    private Button button;

    @Override
    protected void initView(View inflate) {
        button = inflate.findViewById(R.id.btuu);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.tiao();
            }
        });
    }
}
