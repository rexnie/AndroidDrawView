package com.example.android.androiddrawview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn;
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn_draw_clock);
        btn.setOnClickListener(this);

        findViewById(R.id.btn_color_palette).setOnClickListener(this);
        findViewById(R.id.btn_color_matrix).setOnClickListener(this);
        findViewById(R.id.btn_pixel_process).setOnClickListener(this);
        findViewById(R.id.btn_shape_transform).setOnClickListener(this);
        findViewById(R.id.btn_bitmap_mesh).setOnClickListener(this);
        findViewById(R.id.btn_play_view).setOnClickListener(this);
        findViewById(R.id.btn_shader_view).setOnClickListener(this);
        findViewById(R.id.btn_reflect_view).setOnClickListener(this);
        findViewById(R.id.btn_path_effect_view).setOnClickListener(this);
        findViewById(R.id.btn_sinusoid).setOnClickListener(this);
        findViewById(R.id.btn_draw_board).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_draw_clock:
                startActivity(new Intent(this, AnalogClockTestActivity.class));
                break;
            case R.id.btn_color_palette:
                startActivity(new Intent(this, ColorPaletteActivity.class));
                break;
            case R.id.btn_color_matrix:
                startActivity(new Intent(this, ColorMatrixActivity.class));
                break;
            case R.id.btn_pixel_process:
                startActivity(new Intent(this, PixelProcessActivity.class));
                break;
            case R.id.btn_shape_transform:
                startActivity(new Intent(this, ShapeTransformActivity.class));
                break;
            case R.id.btn_bitmap_mesh:
                startActivity(new Intent(this, BitmapMeshActivity.class));
                break;
            case R.id.btn_play_view:
                startActivity(new Intent(this, PlayViewActivity.class));
                break;
            case R.id.btn_shader_view:
                startActivity(new Intent(this, ShaderDemoActivity.class));
                break;
            case R.id.btn_reflect_view:
                startActivity(new Intent(this, ReflectViewActivity.class));
                break;
            case R.id.btn_path_effect_view:
                startActivity(new Intent(this, PathEffectActivity.class));
                break;
            case R.id.btn_sinusoid:
                startActivity(new Intent(this, SinusoidViewActivity.class));
                break;
            case R.id.btn_draw_board:
                startActivity(new Intent(this, DrawBoardActivity.class));
                break;
            default:
                Log.e("MainActivity", "onClick: unknown id");
                break;
        }
    }
}
