package com.example.android.androiddrawview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ShapeTransformActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = "ShapeTransformActivity";
    private ImageView mImageViewShape;
    private ImageView mImageViewShape2;
    private Bitmap mBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_transform);
        mImageViewShape = (ImageView) findViewById(R.id.iv_shape);
        //mImageViewShape2 = (ImageView) findViewById(R.id.iv_shape2);

        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.robot);
        Log.d(TAG, "onCreate: mBmp, w=" + mBmp.getWidth() + ",h=" + mBmp.getHeight());
        mImageViewShape.setImageBitmap(mBmp);

        findViewById(R.id.btn_show_rotate).setOnClickListener(this);
        findViewById(R.id.btn_show_translate).setOnClickListener(this);
        findViewById(R.id.btn_show_scale).setOnClickListener(this);
        findViewById(R.id.btn_show_skew).setOnClickListener(this);
    }

    public void onClick(View v) {
        Bitmap bm;
        switch (v.getId()) {
            case R.id.btn_show_rotate:
                bm = showRotate(mBmp, 90);
                mImageViewShape.setImageBitmap(bm);
                break;
            case R.id.btn_show_translate:
                bm = showTranslate(mBmp);
                mImageViewShape.setImageBitmap(bm);
                break;
            case R.id.btn_show_scale:
                bm = showScale(mBmp);
                mImageViewShape.setImageBitmap(bm);
                break;
        }
    }

    private Bitmap showRotate(Bitmap in, float degrees) {
        int w = in.getWidth();
        int h = in.getHeight();

        Matrix m = new Matrix();
        m.setRotate(degrees);

        return Bitmap.createBitmap(in, 0, 0, w, h, m, false);
    }

    private Bitmap showTranslate(Bitmap in) {
        int w = in.getWidth();
        int h = in.getHeight();

        Matrix m = new Matrix();
        m.setTranslate(10, 10);
        return Bitmap.createBitmap(in, 0, 0, w, h, m, true);

    }

    private Bitmap showScale(Bitmap in) {
        int w = in.getWidth();
        int h = in.getHeight();
        Matrix m = new Matrix();
        m.setScale(0.3f, 0.2f);
        return Bitmap.createBitmap(in, 0, 0, w, h, m, false);
    }
}
