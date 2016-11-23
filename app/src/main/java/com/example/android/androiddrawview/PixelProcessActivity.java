package com.example.android.androiddrawview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static android.graphics.Bitmap.createBitmap;

public class PixelProcessActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = "PixelProcessActivity";
    private ImageView mImageView;
    private Bitmap mBitmapOrg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixel_process);
        mBitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.mm1);

        mImageView = (ImageView) findViewById(R.id.iv_image);
        mImageView.setImageBitmap(mBitmapOrg);

        findViewById(R.id.btn_show_org).setOnClickListener(this);
        findViewById(R.id.btn_show_negative).setOnClickListener(this);
        findViewById(R.id.btn_show_old).setOnClickListener(this);
        findViewById(R.id.btn_show_emboss).setOnClickListener(this);
    }

    public void onClick(View v) {
        Bitmap bm;
        switch (v.getId()) {
            case R.id.btn_show_org:
                mImageView.setImageBitmap(mBitmapOrg);
                break;
            case R.id.btn_show_negative:
                bm = showNegative(mBitmapOrg);
                mImageView.setImageBitmap(bm);
                break;
            case R.id.btn_show_old:
                bm = showOld(mBitmapOrg);
                mImageView.setImageBitmap(bm);
                break;
            case R.id.btn_show_emboss:
                bm = showEmboss(mBitmapOrg);
                mImageView.setImageBitmap(bm);
                break;
            default:
                Log.e(TAG, "onClick: unknown id");

        }
    }

    private Bitmap showNegative(Bitmap org) {
        int r, g, b, a;
        int r1, g1, b1;
        int color;
        int w = org.getWidth();
        int h = org.getHeight();
        int totalPixel = w * h;
        int[] oldPx = new int[totalPixel];
        int[] newPx = new int[totalPixel];

        org.getPixels(oldPx, 0, w, 0, 0 ,w, h);

        for (int i = 0; i < totalPixel; i++) {
            color = oldPx[i];

            a = Color.alpha(color);
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);

            r1 = 255 - r;
            g1 = 255 - g;
            b1 = 255 - b;

            // alpha 不做变换
            newPx[i] = Color.argb(a, r1, g1, b1);
        }

        return createBitmap(newPx, 0, w, w, h, Bitmap.Config.ARGB_8888);
    }

    private Bitmap showOld(Bitmap org) {
        int r, g, b, a;
        int r1, g1, b1;
        int color;
        int w = org.getWidth();
        int h = org.getHeight();
        int totalPixel = w * h;
        int[] oldPx = new int[totalPixel];
        int[] newPx = new int[totalPixel];

        org.getPixels(oldPx, 0, w, 0, 0 ,w, h);

        for (int i = 0; i < totalPixel; i++) {
            color = oldPx[i];

            a = Color.alpha(color);
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            // alpha 不做变换
            newPx[i] = Color.argb(a, r1, g1, b1);
        }

        return createBitmap(newPx, 0, w, w, h, Bitmap.Config.ARGB_8888);
    }

    /*若存在A，B，C 三个像素点，那么B点的浮雕效果算法如下：
    *  B.r = C.r - B.r + 127
    *  B.g = C.g - B.g + 127
    *  B.b = C.b - B.b + 127
    *  alpha 不变
    * */
    private Bitmap showEmboss(Bitmap org) {
        int r1, g1, b1;   // B点最后算法生成的 r, g, b
        int rB, gB, bB, aB; // B点原始的 r, g, b, a
        int rC, gC, bC; // C点原始的 r, g, b
        int colorB; // B点原始的
        int colorC; // C点原始的
        int w = org.getWidth();
        int h = org.getHeight();
        int totalPixel = w * h;
        int[] oldPx = new int[totalPixel];
        int[] newPx = new int[totalPixel];

        org.getPixels(oldPx, 0, w, 0, 0 ,w, h);

        for (int i = 1; i < totalPixel - 1; i++) {
            colorB = oldPx[i];

            aB = Color.alpha(colorB);
            rB = Color.red(colorB);
            gB = Color.green(colorB);
            bB = Color.blue(colorB);

            colorC = oldPx[i+1];

            rC = Color.red(colorC);
            gC = Color.green(colorC);
            bC = Color.blue(colorC);

            r1 = rC - rB + 127;
            g1 = gC - gB + 127;
            b1 = bC - bB + 127;


            if (r1 > 255) {
                r1 = 255;
            } else if (r1 < 0) {
                r1 = 0;
            }

            if (g1 > 255) {
                g1 = 255;
            } else if (g1 < 0) {
                g1 = 0;
            }

            if (b1 > 255) {
                b1 = 255;
            } else if (b1 < 0) {
                b1 = 0;
            }

            // alpha 不做变换
            newPx[i] = Color.argb(aB, r1, g1, b1);
        }

        /*第一个点和最后一个点保持不变*/
        newPx[0] = oldPx[0];
        newPx[totalPixel-1] = oldPx[totalPixel-1];

        return createBitmap(newPx, 0, w, w, h, Bitmap.Config.ARGB_8888);
    }
}
