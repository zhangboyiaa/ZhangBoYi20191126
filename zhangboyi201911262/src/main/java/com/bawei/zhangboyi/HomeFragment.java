package com.bawei.zhangboyi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.bawei.zhangboyi.base.BaseFreagment;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * date:2019/11/26
 * author:张博一(zhangboyi)
 * function:首页面功能
 */
public class HomeFragment extends BaseFreagment {

    private LinearLayout linearLayout;
    private XListView xListView;
    private int page = 1;
    private boolean isloadmore = true;
    List<NewData.ListdataBean> list = new ArrayList<>();


    @Override
    protected void initView(View inflate) {
        linearLayout = inflate.findViewById(R.id.lineares);
        xListView = inflate.findViewById(R.id.xlist);

        //点击事件
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = "https://www.thepaper.cn/newsDetail_forward_4923534";
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                intent.putExtra("key",url);
                startActivity(intent);
            }
        });

        //上下啦
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        //监听
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isloadmore = true;
                getData();
            }

            @Override
            public void onLoadMore() {
                page ++;
                isloadmore = false;
                getData();
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        if (NetState.getInstance().hasNet(getActivity())){
            getData();
            linearLayout.setVisibility(View.GONE);
            xListView.setVisibility(View.VISIBLE);
        }else {
            linearLayout.setVisibility(View.VISIBLE);
            xListView.setVisibility(View.GONE);
        }
    }

    private void getData() {
        String url = "";
        if (page == 1){
            url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
        }else if (page == 2){
            url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
        }else if (page == 3){
            url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
        }

        //调用解析
        NetUtile.getInstance().doGet(url, new NetUtile.MyCallBack() {
            @Override
            public void ondoGetSuccsess(String json) {
                NewData newData = new Gson().fromJson(json, NewData.class);
                Log.i("hh",newData.toString());
                List<NewData.ListdataBean> listdata = newData.getListdata();
                if (isloadmore){
                    list.clear();
                    //刷新时间
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    String format = simpleDateFormat.format(date);
                    xListView.setRefreshTime(format);
                    //刷新
                    xListView.stopRefresh();
                }else {
                    xListView.stopLoadMore();
                }
                list.addAll(listdata);
                xListView.setAdapter(new MyBase(list));
            }

            @Override
            public void ondoGetPhotoSuccsess(Bitmap bitmap) {

            }
        });
    }
}
