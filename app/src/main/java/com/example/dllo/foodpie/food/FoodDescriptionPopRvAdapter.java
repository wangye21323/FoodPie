package com.example.dllo.foodpie.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodDescriptionPopBean;

/**
 * Created by dllo on 16/11/1.
 */
public class FoodDescriptionPopRvAdapter extends RecyclerView.Adapter<FoodDescriptionPopRvAdapter.MyPopViewHolder>{

    private FoodDescriptionPopBean foodDescriptionPopBean;
    private Context context;
    private MyPopViewHolder viewHolder;

    public FoodDescriptionPopRvAdapter(Context context) {
        this.context = context;
    }

    public void setFoodDescriptionPopBean(FoodDescriptionPopBean foodDescriptionPopBean) {
        this.foodDescriptionPopBean = foodDescriptionPopBean;
        notifyDataSetChanged();
    }

    @Override
    public MyPopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food_description_pop, parent, false);
        viewHolder = new MyPopViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyPopViewHolder holder, int position) {
        holder.name.setText(foodDescriptionPopBean.getTypes().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return foodDescriptionPopBean.getTypes() == null ? 0 : foodDescriptionPopBean.getTypes().size();
    }

    public class MyPopViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public MyPopViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_food_description_pop_text);
        }
    }
}

