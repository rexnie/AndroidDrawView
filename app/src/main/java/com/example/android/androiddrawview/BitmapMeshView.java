package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by niedaocai on 16-11-25.
 */

public class BitmapMeshView extends View {
    private static final String TAG = "BitmapMeshView";
    private final int COLUMN_NUMBER_MESH = 800;
    private final int ROW_NUMBER_MESH = 100;
    private final int MESH_LENGTH = (COLUMN_NUMBER_MESH + 1) * (ROW_NUMBER_MESH + 1) * 2;

    private Bitmap mBitmap;
    private float mVerts[];
    private float mOrig[];
    private int mOffset;
    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mm2);
        initArrays();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mOffset += 10;
        if (mOffset == 360) {
            mOffset = 0;
        }
        waveFlag();
        canvas.drawBitmapMesh(mBitmap, COLUMN_NUMBER_MESH, ROW_NUMBER_MESH, mVerts, 0, null, 0, null);
    }

    private void initArrays() {
        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        int index = 0;
        float kx = bitmapWidth / COLUMN_NUMBER_MESH;
        float ky = bitmapHeight / ROW_NUMBER_MESH;

        Log.d(TAG, "initArrays: bitmapWidth=" + bitmapWidth +",bitmapHeight=" + bitmapHeight);
        if (mVerts == null) {
            mVerts = new float[MESH_LENGTH];
        }
        if (mOrig == null) {
            mOrig = new float[MESH_LENGTH];
        }

        int tmpIndex;
        float fx, fy;
        for (int y = 0; y <= ROW_NUMBER_MESH; y++) {
            fy = ky * y;
            for (int x = 0; x <= COLUMN_NUMBER_MESH; x++) {
                fx = kx * x;
                tmpIndex = index * 2;
                mOrig[tmpIndex] = mVerts[tmpIndex] = fx;

                //整体图像下移 200, 避免扭曲后的图像被屏幕遮挡
                mOrig[tmpIndex + 1] = mVerts[tmpIndex + 1] = fy + 200;
                index ++;
            }
        }
    }

    /*
    * 利用正旋曲线画飞扬的旗帜的效果
    * X轴不变化,
    * Y = A*sin(m*x+n)+k
    * A控制振幅，COLUMN_NUMBER_MESH控制X轴的点的数量，也就是控制正旋周期的数量
    * */
    public void waveFlag() {
        int indexY;
        for (int j = 0; j <= ROW_NUMBER_MESH; j++) {
            for(int i = 0; i <= COLUMN_NUMBER_MESH; i++) {
                indexY = (j * (COLUMN_NUMBER_MESH + 1) + i) * 2 + 1;

                // A=20, m=PI/180, n=0, x=i, k=mOrig[indexY]
                float offsetY = (float) Math.sin((float) (i + mOffset) * Math.PI / 180);
                mVerts[indexY] = mOrig[indexY] + offsetY * 20;
            }
        }
    }
}
