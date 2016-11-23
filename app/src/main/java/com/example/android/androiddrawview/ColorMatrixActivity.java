package com.example.android.androiddrawview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ColorMatrixActivity extends AppCompatActivity {
    private static final String TAG = "ColorMatrixActivity";
    private Bitmap mBitmap;
    private int mEditTextWidth;
    private int mEditTextHeight;
    private ImageView mImageView;
    private GridLayout mGroup;
    private EditText mEditTexts[];
    private float mColorMatrixFloat[] = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.allcolors);
        mImageView = (ImageView) findViewById(R.id.iv_color_matrix);

        mGroup = (GridLayout) findViewById(R.id.group);

        mImageView.setImageBitmap(mBitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEditTextWidth = mGroup.getWidth() / 5;
                mEditTextHeight = mGroup.getHeight() / 4;
                addEditTexts();
                initMatrix();
            }
        });
    }

    private void addEditTexts() {
        mEditTexts = new EditText[20];
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            editText.setTextAlignment(EditText.TEXT_ALIGNMENT_CENTER);
            mEditTexts[i] = editText;
            mGroup.addView(editText, mEditTextWidth, mEditTextHeight);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEditTexts[i].setText(String.valueOf(1));
            } else {
                mEditTexts[i].setText(String.valueOf(0));
            }
        }
    }

    /**
     * 获得矩阵值
     */
    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrixFloat[i] = Float.valueOf(mEditTexts[i].getText().toString());
        }
    }

    /**
     * 将矩阵值设置到图像
     */
    private void setImageMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrixFloat);

        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    /**
     * 作用点击事件
     */
    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();
    }

    /**
     * 重置矩阵效果
     */
    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }

}
