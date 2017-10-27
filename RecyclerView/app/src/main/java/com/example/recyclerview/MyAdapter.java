package com.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 潘硕 on 2017/10/27.
 */
class MyAdapter extends RecyclerView.Adapter {
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private View root;

        public ViewHolder(TextView itemView) {
            super(itemView);
            tv = itemView;
        }

        public TextView getTv() {
            return tv;

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        //vh.getTv().setText("Item"+position);  这里获取的是一列数据，下面设置的是显示10条数据(这就看起来和listView展示数据是差不多的，都是一列数据)
        vh.getTv().setText(data[position]);
    }

    @Override
    public int getItemCount() {  //获取recyclerView子对象的数目
        //return 0;
        // return 10;//这里设置为10，说明10个子对象
        return data.length;//返回下面定义的数组的长度,即里面含有多少项
    }

    private String[] data = new String[]{"Hello", "nihao", "nice"};//自定义想要显示的数组里面的数据
}
