package com.example.dllo.foodpie.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    private OnClickPopLeftListener onClickPopLeftListener;

    public FoodDescriptionPopRvAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickPopLeftListener(OnClickPopLeftListener onClickPopLeftListener) {
        this.onClickPopLeftListener = onClickPopLeftListener;
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
    public void onBindViewHolder(MyPopViewHolder holder, final int position) {
        holder.name.setText(foodDescriptionPopBean.getTypes().get(position).getName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPopLeftListener.onClickPopLeft(foodDescriptionPopBean.getTypes().get(position).getIndex(), foodDescriptionPopBean.getTypes().get(position).getName());
                Log.d("FoodDescriptionPopRvAda", foodDescriptionPopBean.getTypes().get(position).getName());
                Log.d("FoodDescriptionPopRvAda", foodDescriptionPopBean.getTypes().get(position).getIndex());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDescriptionPopBean.getTypes() == null ? 0 : foodDescriptionPopBean.getTypes().size();
    }

    public class MyPopViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private final LinearLayout ll;

        public MyPopViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_food_description_pop_text);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_food_description_line);
        }
    }
}

