package com.example.dllo.foodpie.collect;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;

import java.util.ArrayList;

public class CollectActivity extends BaseActivity{

    private TabLayout tab;
    private ViewPager vp;
    private ImageView back;

    @Override
    protected int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initViews() {
        tab = bindView(R.id.tab_collect);
        vp = bindView(R.id.vp_collect);
        back = bindView(R.id.img_collect_img);
    }

    @Override
    protected void initDate() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ArticleFragment());
        fragments.add(new FoodCollectFragment());

        CollectVpAdapter adapter = new CollectVpAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tab.setSelectedTabIndicatorColor(Color.RED);
        tab.setTabTextColors(Color.GRAY, Color.RED);
    }
}