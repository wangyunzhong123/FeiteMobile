package com.example.tianxi.getphonenumber;

/**
 * Created by tianxi on 16-2-24.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class DrawView extends View {
    private Rectangle mRectangle;
    public int width;
    public int height;

    public DrawView(Context context) {
        super(context);

        mRectangle = new Rectangle(context, this);
        mRectangle.setARGB(255, 255, 0, 0);
        mRectangle.setSpeedX(3);
        mRectangle.setSpeedY(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mRectangle.move();
        mRectangle.onDraw(canvas);///并无影响，不是错误

        invalidate();//这个代码放在此函数开头和结尾是一样的，一个效果。。。。。why？？？？是作为一个线程启动的？？
    }

}