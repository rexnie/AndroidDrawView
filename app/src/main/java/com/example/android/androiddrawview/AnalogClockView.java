package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by niedaocai on 16-11-21.
 * 画一个模拟时针表盘，并且每分钟刷新一次
 * 表盘样式见 app/src/main/res/analog_clock_style.png
 */
public class AnalogClockView extends View {
    private static final String TAG = "AnalogClockView";
    private int mWidth;
    private int mHeight;
    private int mClockCenterX;
    private int mClockCenterY;
    private int mClockRadius;
    private int mClockDegreeLen;
    private int mClockDegreeLen2;
    private int mMinuteHandLen;
    private int mHourHandLen;

    private int mHour; //此处是12小时制
    private int mMinute;

    private Paint mPaintCircle;
    private Paint mPaintDegree;
    private Paint mPaintDegree2;
    private Paint mPaintMinute;
    private Paint mPaintHour;

    public AnalogClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        mWidth = p.x;
        mHeight = p.y;

        initClockElement();
        initPaint();
    }

    private void initPaint() {
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStrokeWidth(5);

        mPaintDegree = new Paint(mPaintCircle);
        mPaintDegree.setStrokeWidth(1);
        mPaintDegree.setTextSize(15);

        mPaintDegree2 = new Paint(mPaintCircle);
        mPaintDegree2.setStrokeWidth(3);
        mPaintDegree2.setTextSize(25);

        mPaintHour = new Paint(mPaintCircle);
        mPaintHour.setStrokeWidth(20);

        mPaintMinute = new Paint(mPaintCircle);
        mPaintMinute.setStrokeWidth(10);
    }

    private void initClockElement() {
        mClockCenterX = mWidth / 2;
        mClockCenterY = mHeight / 2;
        mClockRadius  = mWidth / 2 - 20;

        mClockDegreeLen = 10;
        mClockDegreeLen2 = 30;

        mMinuteHandLen = 250;
        mHourHandLen   = 150;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画表盘的外圆圈
        canvas.drawCircle(mClockCenterX, mClockCenterY, mClockRadius, mPaintCircle);

         //把坐标原点移动到表盘的圆心
        canvas.translate(mClockCenterX, mClockCenterY);

        for(int i = 1; i <= 60; i++) {
            // 旋转60次，每次转6度，这样每次都在12点钟方向画表盘的刻度和数字
            canvas.rotate(6);
            if (i % 5 == 0) {
                String text = String.valueOf(i / 5);
                //画大刻度
                canvas.drawLine(0, -mClockRadius,
                        0, mClockDegreeLen2 - mClockRadius, mPaintDegree2);
                //画数字
                canvas.drawText(text, -mPaintDegree2.measureText(text)/2, mClockDegreeLen2 - mClockRadius + 30,
                        mPaintDegree2);
            } else {
                //画小刻度
                canvas.drawLine(0, -mClockRadius,
                        0, mClockDegreeLen - mClockRadius, mPaintDegree);
            }
        }

        float hourDegree = mHour * 30 + mMinute / 2; //一小时30度，加上分钟的偏移
        canvas.rotate(hourDegree);
        // 画时针
        canvas.drawLine(0, 0, 0, -mHourHandLen, mPaintHour);

        canvas.rotate(360 - hourDegree + mMinute * 6);
        // 画分针
        canvas.drawLine(0, 0, 0, -mMinuteHandLen, mPaintMinute);
    }

    public void setTime(int hour, int min) {
        mMinute = min;
        mHour = hour;
    }
}
