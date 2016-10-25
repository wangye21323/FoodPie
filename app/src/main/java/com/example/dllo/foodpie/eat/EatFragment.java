package com.example.dllo.foodpie.eat;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/21.
 */
public class EatFragment extends BaseFragment{

    private TabLayout tabEat;
    private ViewPager vpEat;

    @Override
    protected int getLayout() {
        return R.layout.fragment_eat;
    }

    @Override
    protected void initView() {
        tabEat = bindView(R.id.tab_eat);
        vpEat = bindView(R.id.vp_eat);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new HomePageFragment());
        arrayList.add(new TestFragment());
        arrayList.add(new KnowledgeFragment());
        arrayList.add(new BeautyFood());

        EatVpAdapter adapter = new EatVpAdapter(getChildFragmentManager());
        vpEat.setAdapter(adapter);
        adapter.setFragments(arrayList);
        adapter.notifyDataSetChanged();
        tabEat.setupWithViewPager(vpEat);
    }
}
