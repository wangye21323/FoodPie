package com.example.dllo.foodpie.collect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.CommonViewHolder;
import com.example.dllo.foodpie.databean.CollectLvBean;
import com.example.dllo.foodpie.eat.DescriptionActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/14.
 */
public class ArticleLvAdapter extends BaseAdapter{
    private ArrayList<CollectLvBean> beanArrayList;
    private Context context;

    public ArticleLvAdapter(Context context) {
        this.context = context;
    }

    public void setBeanArrayList(ArrayList<CollectLvBean> beanArrayList) {
        this.beanArrayList = beanArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanArrayList == null ? 0 : beanArrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_collect_article);
        commonViewHolder.setText(R.id.item_collect_article_title, beanArrayList.get(position).getTitle()).setViewClick(R.id.ll_collect_article_all, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ArticleLvAdapter", "点击了" + position);
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("Web", beanArrayList.get(position).getUrl());
                intent.putExtra("Title", beanArrayList.get(position).getTitle());
                intent.putExtra("Text","咨询详情");
                context.startActivity(intent);
            }
        });
        return commonViewHolder.getItemView();
    }
}
