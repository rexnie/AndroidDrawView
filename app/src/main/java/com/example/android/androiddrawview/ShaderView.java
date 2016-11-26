package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by niedaocai on 26/11/2016.
 */
public class ShaderView extends View {
    private Bitmap mBitmapMM2;
    private Bitmap mBitmapRobot;

    private Paint mPaint;
    private Shader mShaderClamp;
    private Shader mShaderRepeat;

    private Shader mLinearGradient;


    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmapMM2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.robot);
        mBitmapRobot = BitmapFactory.decodeResource(context.getResources(), R.drawable.robot);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint();
        mShaderClamp = new BitmapShader(mBitmapMM2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //当图小于画图区时，重复最后的像素
        mShaderRepeat = new BitmapShader(mBitmapRobot, Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        //颜色渐变效果
        mLinearGradient = new LinearGradient(0, 1200, 1600, 1600,
                Color.YELLOW, Color.BLUE, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShader(mShaderClamp);
        //canvas.drawCircle(600, 220, 200, mPaint); //for mm2 png, 600, 220, 200

        canvas.drawCircle(600, 220, 700, mPaint); // robot, clamp

        mPaint.setShader(mShaderRepeat);
        canvas.drawCircle(800, 800, 300, mPaint);

        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0, 1200, 1600, 1600, mPaint);
    }
}

