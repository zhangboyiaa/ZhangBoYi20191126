package com.bawei.zhangboyi;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/11/26
 * author:张博一(zhangboyi)
 * function:适配器
 */
public class MyBase extends BaseAdapter {

    List<NewData.ListdataBean> list = new ArrayList<>();

    private int TYPE_ONE = 0;
    private int TYPE_TWO = 1;


    public MyBase(List<NewData.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = list.get(position).getItemType();
        if (itemType == 1){
            return TYPE_ONE;
        }else if (itemType == 2){
            return TYPE_TWO;
        }
        return TYPE_ONE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        int itemViewType = getItemViewType(position);
        if (convertView == null){
            if (itemViewType == TYPE_ONE){
                convertView = View.inflate(parent.getContext(),R.layout.xlist_one,null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.one_image);
                holder.title = convertView.findViewById(R.id.one_title);
                holder.content = convertView.findViewById(R.id.one_content);
                convertView.setTag(holder);
            }else if (itemViewType == TYPE_TWO){
                convertView = View.inflate(parent.getContext(),R.layout.xlist_two,null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.two_image);
                holder.title = convertView.findViewById(R.id.two_title);
                holder.content = convertView.findViewById(R.id.two_content);
                convertView.setTag(holder);
            }
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewData.ListdataBean listdataBean = list.get(position);

        holder.title.setText(listdataBean.getTitle());
        holder.content.setText(listdataBean.getContent());

        final ViewHolder holder1 = holder;

        NetUtile.getInstance().doGetPhoto(listdataBean.getImageurl(), new NetUtile.MyCallBack() {
            @Override
            public void ondoGetSuccsess(String json) {

            }

            @Override
            public void ondoGetPhotoSuccsess(Bitmap bitmap) {
                holder1.imageView.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView title,content;
    }
}
