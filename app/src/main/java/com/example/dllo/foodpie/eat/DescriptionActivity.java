package com.example.dllo.foodpie.eat;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.databean.CollectBean;
import com.example.dllo.foodpie.dbtool.CollectDBTool;

import java.util.List;

public class DescriptionActivity extends BaseActivity implements View.OnTouchListener {

    private WebView webView;
    private String urlIntent;
    private ImageButton iBtnBack;
    private TextView tvDescription;
    private String text;
    //手指向右滑动时的最小速度
    private static final int XSPEED_MIN = 200;
    //手指向右滑动时的最小距离
    private static final int XDISTANCE_MIN = 150;
    //记录手指按下时的横坐标。
    private float xDown;
    //记录手指移动时的横坐标。
    private float xMove;
    //用于计算手指滑动的速度。
    private VelocityTracker mVelocityTracker;
    private RelativeLayout rl;
    private RelativeLayout rlShare;
    private String title;
    private boolean isClick = false;
    private ImageView imgHeart;
    private TextView tvCollect;


    @Override
    protected int getLayout() {
        return R.layout.activity_description;
    }

    @Override
    protected void initViews() {

        Intent intent = getIntent();
        urlIntent = intent.getStringExtra("Web");
        text = intent.getStringExtra("Text");
        title = intent.getStringExtra("Title");



        webView = bindView(R.id.wv_eat);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DescriptionActivity.this.onTouch(v,event);
                Log.d("DescriptionActivity", "webView");
                return false;
            }
        });
        iBtnBack = bindView(R.id.iBtn_Eat_back);

        imgHeart = bindView(R.id.img_eat_test_heart);
        tvCollect = bindView(R.id.tv_eat_test_text);

        tvDescription = bindView(R.id.tv_eat_description);
        rl = bindView(R.id.rl_eat_description_back);
        rlShare = bindView(R.id.rv_description_share);
        rlShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClick){
                    imgHeart.setImageResource(R.mipmap.ic_favorate_checked);
                    tvCollect.setText("已收藏");
                    tvCollect.setSelected(true);
                    CollectBean bean = new CollectBean();
                    bean.setTitle(title);
                    bean.setUrl(urlIntent);
                    CollectDBTool.getInstance().insertCollectBean(bean);
                    isClick = !isClick;
                }else{
                    tvCollect.setSelected(false);
                    tvCollect.setText("收藏");
                    imgHeart.setImageResource(R.mipmap.ic_favorate_unchecked);
                    CollectDBTool.getInstance().deleteValueBean(CollectBean.class,"title",new String[]{title});
                    isClick = !isClick;
                }

            }
        });
         CollectDBTool.getInstance().queryByValuesCollectBean(CollectBean.class, "title", new String[]{title}, new CollectDBTool.OnQueryListener() {
             @Override
             public void onQuery(List<CollectBean> collectBean) {
                 if (collectBean.size() > 0){
                     imgHeart.setImageResource(R.mipmap.ic_favorate_checked);
                     tvCollect.setText("已收藏");
                     tvCollect.setSelected(true);
                 }else {
                     tvCollect.setSelected(false);
                     tvCollect.setText("收藏");
                     imgHeart.setImageResource(R.mipmap.ic_favorate_unchecked);
                 }
             }
         });


    }

    @Override
    protected void initDate() {
        tvDescription.setText(text);
        webView.loadUrl(urlIntent);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        rl.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                int distanceX = (int) (xMove - xDown);
                int xSpeed = getScrollVelocity();
                if(distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN) {
                    finish();
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;
        }
        return true;
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }
}