package com.example.dllo.foodpie.collect;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.databean.CollectBean;
import com.example.dllo.foodpie.databean.CollectLvBean;
import com.example.dllo.foodpie.dbtool.CollectDBTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/12.
 */
public class ArticleFragment extends BaseFragment{

    private ListView lv;
    private ArticleLvAdapter adapter;
    private ImageView imgEmpty;
    private TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_artivle;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_collect_article);
        imgEmpty = bindView(R.id.img_collect_article_empty);
        adapter = new ArticleLvAdapter(getActivity());
        tv = bindView(R.id.tv_collect_article_empty);
        lv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        CollectDBTool.getInstance().queryAllCollectBean(new CollectDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<CollectBean> collectBean) {
                ArrayList<CollectLvBean> been = new ArrayList<>();
                for (int i = 0; i < collectBean.size(); i++) {
                    CollectLvBean bean = new CollectLvBean();
                    bean.setTitle(collectBean.get(i).getTitle());
                    bean.setUrl(collectBean.get(i).getUrl());
                    been.add(bean);
                }
                adapter.setBeanArrayList(been);
                //判断当前页是否有数据, 有数据那么把图标隐藏
                if (been.size() != 0){
                    imgEmpty.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                }else {
                    imgEmpty.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
            }

        });

    }

    @Override
    //返回时, 刷新一下列表
    public void onResume() {
        super.onResume();
        CollectDBTool.getInstance().queryAllCollectBean(new CollectDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<CollectBean> collectBean) {
                ArrayList<CollectLvBean> been = new ArrayList<>();
                for (int i = 0; i < collectBean.size(); i++) {
                    CollectLvBean bean = new CollectLvBean();
                    bean.setTitle(collectBean.get(i).getTitle());
                    bean.setUrl(collectBean.get(i).getUrl());
                    been.add(bean);
                }
                adapter.setBeanArrayList(been);
            }

        });
    }
}