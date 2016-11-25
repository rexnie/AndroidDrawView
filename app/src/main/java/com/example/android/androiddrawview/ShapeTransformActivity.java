package com.example.android.androiddrawview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

public class ShapeTransformActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = "ShapeTransformActivity";
    private ShapeView mShapeView;
    private Bitmap mBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_transform);
        mShapeView = (ShapeView) findViewById(R.id.shape_view);

        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.robot);

        mShapeView.setBitmap(mBmp);

        findViewById(R.id.btn_show_orginal).setOnClickListener(this);
        findViewById(R.id.btn_show_rotate).setOnClickListener(this);
        findViewById(R.id.btn_show_translate).setOnClickListener(this);
        findViewById(R.id.btn_show_scale).setOnClickListener(this);
        findViewById(R.id.btn_show_skew).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_orginal:
                showOriginal();
                break;
            case R.id.btn_show_rotate:
                showRotate(45);
                break;
            case R.id.btn_show_translate:
                showTranslate(300, 500);
                break;
            case R.id.btn_show_scale:
                showScale(.2f, .5f);
                break;
            case R.id.btn_show_skew:
                showSkew(0, 0.5f);
                break;
        }
    }

    private void showRotate(float degrees) {
        Matrix m = new Matrix();
        m.setRotate(degrees);
        mShapeView.setMatrix(m);
        mShapeView.invalidate();
    }

    private void showTranslate(float dx, float dy) {
        Matrix m = new Matrix();
        m.setTranslate(dx, dy);
        mShapeView.setMatrix(m);
        mShapeView.invalidate();
    }

    private void showScale(float sx, float sy) {
        Matrix m = new Matrix();
        m.setScale(sx, sy);
        mShapeView.setMatrix(m);
        mShapeView.invalidate();
    }

    private void showSkew(float kx, float ky) {
        Matrix m = new Matrix();
        m.setSkew(kx, ky);
        mShapeView.setMatrix(m);
        mShapeView.invalidate();
    }

    private void showOriginal() {
        mShapeView.setMatrix(null);
        mShapeView.invalidate();
    }
}

class ShapeView extends View {
    private Bitmap mBitmapOrg;
    private Matrix mMatrix;
    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    protected void setBitmap(Bitmap org) {
        if (org != null) {
            mBitmapOrg = org;
        }
    }

    protected void setMatrix(Matrix m) {
        mMatrix = m;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMatrix != null) {
            canvas.drawBitmap(mBitmapOrg, mMatrix, null);
        } else {
            canvas.drawBitmap(mBitmapOrg, 0, 0, null);
        }
    }
}
