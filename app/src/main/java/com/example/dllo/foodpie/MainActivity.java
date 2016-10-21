package com.example.dllo.foodpie;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.foodpie.base.BaseActivity;

public class MainActivity extends BaseActivity{
    private RadioButton rbMainFood;
    private RadioButton rbMainEat;
    private RadioButton rbMainMy;
    private RadioGroup rgMain;
    private TextView tvFood;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        rbMainFood = bindView(R.id.rb_food);
        rbMainEat = bindView(R.id.rb_eat);
        rbMainMy = bindView(R.id.rb_my);
        rgMain = bindView(R.id.rg_main);
        tvFood = bindView(R.id.tv_main_food);

    }


    @Override
    protected void initDate() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_food:
                        Toast.makeText(MainActivity.this, "food", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_eat:
                        Toast.makeText(MainActivity.this, "eat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_my:
                        Toast.makeText(MainActivity.this, "my", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}
