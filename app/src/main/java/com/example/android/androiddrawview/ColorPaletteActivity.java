package com.example.android.androiddrawview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorPaletteActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "ColorPaletteActivity";
    private int mColorRed;
    private int mColorBlue;
    private int mColorGreen;
    private int mColorAlpha;

    private TextView mTextInput;
    private ColorPaletteView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_palette);
        mTextInput = (TextView) findViewById(R.id.tv_color_select);
        mView = (ColorPaletteView) findViewById(R.id.view_color);


        SeekBar sb;
        ((SeekBar) findViewById(R.id.sb_r)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sb_g)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.sb_b)).setOnSeekBarChangeListener(this);
        sb = (SeekBar) findViewById(R.id.sb_a);
        sb.setOnSeekBarChangeListener(this);
        sb.setProgress(255);
        mColorAlpha = 255;
        updateColor();
    }

    private void updateColor() {
        updateTextView();
        mView.setColor(Color.argb(mColorAlpha, mColorRed, mColorGreen, mColorBlue));
    }

    private void updateTextView() {
        StringBuilder sb = new StringBuilder("Red=");
        sb.append(mColorRed);
        sb.append(", Green=" + mColorGreen);
        sb.append(", Blue=" + mColorBlue);
        sb.append(", Alpha=" + mColorAlpha);
        mTextInput.setText(sb.toString());
        mTextInput.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_r:
                mColorRed = progress;
                break;
            case R.id.sb_g:
                mColorGreen = progress;
                break;
            case R.id.sb_b:
                mColorBlue = progress;
                break;
            case R.id.sb_a:
                mColorAlpha = progress;
                break;
        }
        updateColor();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}
}
