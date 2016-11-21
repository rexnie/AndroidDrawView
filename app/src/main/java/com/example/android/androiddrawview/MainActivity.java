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
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_draw_clock:
                startActivity(new Intent(this, AnalogClockTestActivity.class));
                break;
            default:
                Log.e("MainActivity", "onClick: unknown id");
        }
    }
}
