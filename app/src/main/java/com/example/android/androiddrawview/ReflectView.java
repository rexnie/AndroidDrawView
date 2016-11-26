package com.example.android.androiddrawview;

import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * 倒影
 * Created by niedaocai on 26/11/2016.

 */
public class ReflectView extends View {

    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode xfermode;

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mm2);
        Matrix matrix = new Matrix();
        // 反转图像
        matrix.setScale(1F, -1F);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,
                mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(),
                0, mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        mPaint.setXfermode(xfermode);
        //绘制渐变效果矩形
        canvas.drawRect(0, mSrcBitmap.getHeight(),
                mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }
}