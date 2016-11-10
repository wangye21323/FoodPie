package com.example.dllo.foodpie.food;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.CommonViewHolder;
import com.example.dllo.foodpie.databean.FoodAnalyzeBean;

/**
 * Created by dllo on 16/11/10.
 */
public class AnalyzeRvAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private FoodAnalyzeBean foodAnalyzeBean;
    //    private static final int TYPE_HISTORY = 3;
    private static final int TYPE_LEFT = 0;
    private static final int TYPE_CENTER = 1;
    private static final int TYPE_RIGHT = 2;

    public void setFoodAnalyzeBean(FoodAnalyzeBean foodAnalyzeBean) {
        this.foodAnalyzeBean = foodAnalyzeBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        switch (viewType){
            case TYPE_LEFT:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_analyze_left);
                break;
            case TYPE_CENTER:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_analyze_center);
                break;
            case TYPE_RIGHT:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_analyze_right);
                break;
        }
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_LEFT:
                if (foodAnalyzeBean != null){
                    holder.setText(R.id.tv_analyze_item_left, foodAnalyzeBean.getNutrition().get(position).getValue());
                    holder.setText(R.id.tv_analyze_item_left_unit, foodAnalyzeBean.getNutrition().get(position).getUnit());
                }
                break;
            case TYPE_CENTER:
                if (foodAnalyzeBean != null){
                    holder.setText(R.id.tv_analyze_item_center, foodAnalyzeBean.getNutrition().get(position).getName());
                }
                break;
            case TYPE_RIGHT:
                if (foodAnalyzeBean != null){
                    holder.setText(R.id.tv_analyze_item_right, foodAnalyzeBean.getNutrition().get(position).getValue());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return foodAnalyzeBean.getNutrition() == null ? 0 : foodAnalyzeBean.getNutrition().size();
    }
}
