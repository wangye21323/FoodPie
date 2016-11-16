package com.example.dllo.foodpie.collect;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.databean.FoodAllDbBean;
import com.example.dllo.foodpie.databean.FoodCollectBean;
import com.example.dllo.foodpie.dbtool.FoodAllDBTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/12.
 */
public class FoodCollectFragment extends BaseFragment{

    private ListView lvCollect;
    private FoodCollectLvAdapter adapter;
    private TextView tvEmpty;
    private ImageView imgEmpty;

    @Override
    protected int getLayout() {
        return R.layout.fragment_food_collect;
    }

    @Override
    protected void initView() {
        lvCollect = bindView(R.id.lv_collect_food);
        adapter = new FoodCollectLvAdapter(getActivity());
        lvCollect.setAdapter(adapter);
        tvEmpty = bindView(R.id.tv_collect_food_collect_empty);
        imgEmpty = bindView(R.id.img_collect_food_collect_empty);
    }

    @Override
    protected void initData() {
        final ArrayList<FoodCollectBean> been = new ArrayList<>();
        FoodAllDBTool.getInstance().queryAllFoodAllDbBean(new FoodAllDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<FoodAllDbBean> foodAllDbBean) {
                for (int i = 0; i < foodAllDbBean.size(); i++) {
                    FoodCollectBean bean = new FoodCollectBean();
                    bean.setName(foodAllDbBean.get(i).getName());
                    bean.setWeight(foodAllDbBean.get(i).getCalory());
                    bean.setUrl(foodAllDbBean.get(i).getLarge_image_url());
                    bean.setCode(foodAllDbBean.get(i).getCode());
                    been.add(bean);
                }
                adapter.setFoodCollectBeen(been);

                //判断当前页是否有数据, 有数据那么把图标隐藏
                if (been.size() != 0){
                    imgEmpty.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.GONE);
                }else {
                    imgEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final ArrayList<FoodCollectBean> been = new ArrayList<>();
        FoodAllDBTool.getInstance().queryAllFoodAllDbBean(new FoodAllDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<FoodAllDbBean> foodAllDbBean) {
                for (int i = 0; i < foodAllDbBean.size(); i++) {
                    FoodCollectBean bean = new FoodCollectBean();
                    bean.setName(foodAllDbBean.get(i).getName());
                    bean.setWeight(foodAllDbBean.get(i).getCalory());
                    bean.setUrl(foodAllDbBean.get(i).getLarge_image_url());
                    bean.setCode(foodAllDbBean.get(i).getCode());
                    been.add(bean);
                }
                adapter.setFoodCollectBeen(been);
                //判断当前页是否有数据, 有数据那么把图标隐藏
                if (been.size() != 0){
                    imgEmpty.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.GONE);
                }else {
                    imgEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}