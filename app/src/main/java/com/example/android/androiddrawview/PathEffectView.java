package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by niedaocai on 26/11/2016.
 */
public class PathEffectView extends View {
    private Path mPath;
    private PathEffect[] mEffect = new PathEffect[6];
    private Paint mPaint;

    /**
     * 构造方法
     * @param context
     * @param attrs
     */
    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPath.moveTo(0,0);
        for (int i = 0; i <= 30; i++){
            mPath.lineTo(i*35,(float)(Math.random()*100));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mEffect[0] = null;
        //圆角特效
        mEffect[1] = new CornerPathEffect(30);

        //线段上杂点特效
        mEffect[2] = new DiscretePathEffect(3.0F,5.0F);

        //画虚线特效
        mEffect[3] = new DashPathEffect(new float[]{20,10,5,10},0);
        Path path = new Path();
        path.addRect(0,0,8,8,Path.Direction.CCW);

        //画虚线特效，可设置虚线上点的形状，方形点还是圆形点
        mEffect[4]= new PathDashPathEffect(path,12,0,PathDashPathEffect.Style.ROTATE);

        //组合特效
        mEffect[5] = new ComposePathEffect(mEffect[3],mEffect[1]);
        for (int i = 0; i<mEffect.length;i++){
            mPaint.setPathEffect(mEffect[i]);
            canvas.drawPath(mPath,mPaint);
            canvas.translate(0,200);
        }
    }
}

