package com.example.dllo.foodpie;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.eat.EatFragment;
import com.example.dllo.foodpie.food.FoodFragment;
import com.example.dllo.foodpie.my.MyFragment;

public class MainActivity extends BaseActivity {
    private RadioButton rbMainFood;
    private RadioButton rbMainEat;
    private RadioButton rbMainMy;

    private RadioButton last;
    private RadioGroup rgMain;
    private TextView tvFood;
    private View framMain;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    private MyFragment myFragment;
    private EatFragment eatFragment;
    private FoodFragment foodFragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        framMain = bindView(R.id.frame_main);
        rbMainFood = bindView(R.id.rb_food);
        rbMainEat = bindView(R.id.rb_eat);
        rbMainMy = bindView(R.id.rb_my);
        rgMain = bindView(R.id.rg_main);
    }


    @Override
    protected void initDate() {

        myFragment = new MyFragment();
        eatFragment = new EatFragment();
        foodFragment = new FoodFragment();
        manager = getSupportFragmentManager();

        //第一次载入界面时显示第一个界面
        initLast(rbMainFood);
        rbMainFood.setSelected(true);
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_main, foodFragment);
        transaction.commit();

        //首页的下方RadioButton的点击切换
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();

                switch (checkedId) {
                    case R.id.rb_food:
                        initLast(rbMainFood);
                        rbMainFood.setSelected(true);
                        transaction.replace(R.id.frame_main, foodFragment);
                        break;
                    case R.id.rb_eat:
                        initLast(rbMainEat);
                        rbMainEat.setSelected(true);
                        transaction.replace(R.id.frame_main, eatFragment);
                        break;
                    case R.id.rb_my:
                        initLast(rbMainMy);
                        rbMainMy.setSelected(true);
                        transaction.replace(R.id.frame_main, myFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }

    //当点击其他的时候, 字体颜色还原本色
    private void initLast(RadioButton radioButton) {
        if(last != null){
            last.setSelected(false);
            last = radioButton;
        }else {
            last = radioButton;
        }
    }
}
