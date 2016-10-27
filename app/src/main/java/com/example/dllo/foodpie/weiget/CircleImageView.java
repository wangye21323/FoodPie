package com.example.dllo.foodpie.weiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.dllo.foodpie.R;


/**
 * Created by dllo on 16/9/27.
 */
public class CircleImageView extends ImageView{
    private boolean isCircle;

    //是在代码里面初始化组件的时候调用这个构造方法
    public CircleImageView(Context context) {
        super(context);
    }

    //在XML里面布局这个组件的时候执行这个构造方法
    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取XML布局里设置的自定义组件的属性值
        //下边的方法时找到attrs.xml中自定义组件设置的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        isCircle = array.getBoolean(R.styleable.CircleImageView_isCircle, false);//图片默认应该不是圆的
    }

    //自定义组件的style的时候会执行这个构造方法

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isCircle){
            //这里绘制一个圆形的图片
            //获取到inageView的src资源
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable != null){
                //将src设置的图片装换成bitmap类型     (android的所有图片都会转成bitmap形式)
                Bitmap bitmap = drawable.getBitmap();
                //获取一个圆形的bitmap
                Bitmap circleBitmap = getCircleBitmap(bitmap);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
//                Rect rect = new Rect(0, 0, circleBitmap.getWidth(), circleBitmap.getHeight());
                canvas.drawBitmap(circleBitmap, 0, 0, paint);
            }


        }else{
            //这个父类方法里有一些实现方法, 可以帮助组件显示
            super.onDraw(canvas);
        }
    }

    private Bitmap changeSize(Bitmap bitmap){
        int width,height;
        width = getWidth();
        height = getHeight();
        bitmap = Bitmap.createScaledBitmap(bitmap,width,height,false);
        return bitmap;
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        //初始化一个空跟src一样大小的bitmap
        bitmap = changeSize(bitmap);
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建一个空的装载bitmap的画布
        Canvas canvas = new Canvas(outBitmap);
        //初始化一个画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);

        //设置画笔的模式, 两者相交去掉前景模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int radio = this.getHeight() / 2;
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }
}
