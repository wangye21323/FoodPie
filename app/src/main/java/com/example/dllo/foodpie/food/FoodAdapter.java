package com.example.dllo.foodpie.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.eat.OnClickItem;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/27.
 */
public class FoodAdapter extends BaseAdapter {
    private FoodBean.GroupBean groupBean;
    private Context context;
    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public FoodAdapter(Context context) {
        this.context = context;
    }

    public void setGroupBean(FoodBean.GroupBean groupBean) {
        this.groupBean = groupBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return groupBean == null ? 0 : groupBean.getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return groupBean.getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyTypeViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
            viewHolder = new MyTypeViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyTypeViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(groupBean.getCategories().get(position).getName());
        String imgUrlCard = groupBean.getCategories().get(position).getImage_url();
        VolleySingleton.getInstance().getImage(imgUrlCard, viewHolder.image);

        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(String.valueOf(groupBean.getCategories().get(position).getId()));
            }
        });

        return convertView;
    }

    private class MyTypeViewHolder {

        private ImageView image;
        private TextView text;
        private LinearLayout ll;

        public MyTypeViewHolder(View convertView) {
            image = (ImageView) convertView.findViewById(R.id.img_food_type_picture);
            text = (TextView) convertView.findViewById(R.id.tv_food_type_text);
            ll = (LinearLayout) convertView.findViewById(R.id.ll_food_item);
        }
    }
}
