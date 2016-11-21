package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by niedaocai on 16-11-21.
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

    private int mHour;
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
        Log.d(TAG, "AnalogClockView: mWidth=" + mWidth + ",mHeight=" + mHeight);

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

        canvas.drawCircle(mClockCenterX, mClockCenterY, mClockRadius, mPaintCircle);
        canvas.translate(mClockCenterX, mClockCenterY);

        for(int i = 0; i < 24; i++) {
            String text = String.valueOf(i);
            if (i == 0 || i == 6 || i == 12 | i == 18) {
                canvas.drawLine(0, -mClockRadius,
                        0, mClockDegreeLen2 - mClockRadius, mPaintDegree2);
                canvas.drawText(text, -mPaintDegree2.measureText(text)/2, mClockDegreeLen2 - mClockRadius + 30,
                        mPaintDegree2);
            } else {
                canvas.drawLine(0, -mClockRadius,
                        0, mClockDegreeLen - mClockRadius, mPaintDegree);
                canvas.drawText(text, -mPaintDegree.measureText(text)/2, mClockDegreeLen - mClockRadius + 20,
                        mPaintDegree);
            }
            canvas.rotate(15);
        }

        canvas.rotate(mHour * 15);
        canvas.drawLine(0, 0, 0, -mHourHandLen, mPaintHour);

        canvas.rotate(360 - mHour * 15 + mMinute * 6);
        canvas.drawLine(0, 0, 0, -mMinuteHandLen, mPaintMinute);
    }

    public void setTime(int hour, int min) {
        mMinute = min;
        mHour = hour;
    }
}
