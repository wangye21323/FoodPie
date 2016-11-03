package com.example.dllo.foodpie.food;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.web.VolleySingleton;

import java.util.List;

/**
 * Created by dllo on 16/10/27.
 */
public class FoodAdapter extends BaseAdapter {
    private FoodBean foodBean;
    private Context context;
    private OnClickFoodListener onClickFoodListener;
    private int group = -1;

    public void setOnClickFoodListener(OnClickFoodListener onClickFoodListener) {
        this.onClickFoodListener = onClickFoodListener;
    }

    public FoodAdapter(Context context) {
        this.context = context;
    }

    public void setFoodBean(int group, FoodBean foodBean) {
        this.foodBean = foodBean;
        this.group = group;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if(foodBean == null){
            return 0;
        }
        return foodBean.getGroup().get(group).getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return foodBean.getGroup().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyTypeViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
            viewHolder = new MyTypeViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyTypeViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(foodBean.getGroup().get(group).getCategories().get(position).getName());
        String imgUrlCard = foodBean.getGroup().get(group).getCategories().get(position).getImage_url();
        VolleySingleton.getInstance().getImage(imgUrlCard, viewHolder.image);


        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodBean.getGroup().get(group).getCategories().get(position).getName();
                int id = foodBean.getGroup().get(group).getCategories().get(position).getId();
//                ArrayList categories = (ArrayList) foodBean.getGroup().get(group).getCategories().get(position).getSub_categories();
                List<FoodBean.GroupBean.CategoriesBean.SubCategoriesBean> been = foodBean.getGroup().get(group).getCategories().get(position).getSub_categories();
                Log.d("FoodAdapter", "categories:" + been);
                onClickFoodListener.onClickFood(id, group, name, been);
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
