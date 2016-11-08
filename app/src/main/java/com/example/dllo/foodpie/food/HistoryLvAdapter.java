package com.example.dllo.foodpie.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.HistoryBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/8.
 */
public class HistoryLvAdapter extends BaseAdapter{
    private ArrayList<HistoryBean> historyBean;
    private Context context;

    public void setHistoryBean(ArrayList<HistoryBean> historyBean) {
        this.historyBean = historyBean;
        notifyDataSetChanged();
    }

    public HistoryLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return historyBean.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(historyBean.get(position).getName());

        return convertView;
    }

    private class MyViewHolder {

        private TextView tv;

        public MyViewHolder(View convertView) {
            tv = (TextView) convertView.findViewById(R.id.tv_history);
        }
    }
}
