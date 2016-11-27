package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by niedaocai on 26/11/2016.
 */

public class SinusoidView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "SinusoidView";
    //SurfaceHolder
    private SurfaceHolder mHolder;
    //用于绘制的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    private int x, y;
    private Path mPath;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public SinusoidView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    /*如下三个SurfaceHolder.Callback中的方法调用在主线程*/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            x += 1;
            if (x == mWidth) {
                x = 0;
                mPath.reset();
            }
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);

            // 避免主线程退出后子线程继续画图
            if (mIsDrawing) {
                draw();
            }
            /*
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }*/
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            //提交
        } catch (Exception e) {
            // 主线程退出后子线程继续画图时，mCanvas为空
            // catch NullPointerException
        } finally {
            // 加if是为了避免出现IllegalStateException
            // java.lang.IllegalStateException: Surface has already been released.
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
