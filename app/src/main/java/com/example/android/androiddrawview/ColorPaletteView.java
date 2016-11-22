package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by niedaocai on 16-11-22.
 */

public class ColorPaletteView extends View {
    private static final String TAG = "ColorPaletteView";
    private Paint mPaint;

    public ColorPaletteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 210, 200, mPaint);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }
}
