package com.example.dllo.foodpie.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/31.
 * 通用的ViewHolder
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{
    //用法和hashmap一样, 但是key的固定值是int类型, 用它来存放所有的view, key就是view的id
    private SparseArray<View> views;
    private View itemView;//行布局

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 通过View的id来获取指定的View
     * 如果该View没有赋值, 就限制性findViewById
     * 然后把它放到View的集合里面
     * 使用泛型来取消强转
     * @param id
     * @return 指定View
     */
    public <T extends View>T getView(int id){
        View view = views.get(id);
        if (view == null){
            //证明SparseArray里面没有这个View
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }
    //专门给ListView使用的方法
    public static CommonViewHolder getViewHolder(View itemView, ViewGroup parent, int itemId){
        CommonViewHolder commonViewHolder;
        if (itemView == null){
            Context context = parent.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, parent, false);
            commonViewHolder = new CommonViewHolder(itemView);
            itemView.setTag(commonViewHolder);
        }else {
            commonViewHolder = (CommonViewHolder) itemView.getTag();
        }
        return commonViewHolder;
    }

    //专门给RecyclerView使用的方法
    public static CommonViewHolder getViewHolder(ViewGroup parent, int itemId){
        return getViewHolder(null, parent, itemId);
    }
    public View getItemView() {
        return itemView;
    }

    /************ViewHolder 设置数据的方法************/
    //设置文字
    public CommonViewHolder setText(int id, String text){
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }
    public CommonViewHolder setImage(int id, int imgId){
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }
    public CommonViewHolder setImage(int id, String url){
        ImageView imageView = getView(id);
        return this;
    }

    public CommonViewHolder setViewClick(int id, View.OnClickListener listener){
        getView(id).setOnClickListener(listener);
        return this;
    }

    //给行布局设置点击事件
    public CommonViewHolder setItemClick(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }
}
