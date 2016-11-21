package com.example.android.androiddrawview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class AnalogClockTestActivity extends AppCompatActivity {
    private static final String TAG = "AnalogClockTestActivity";
    private static final String ACTION_TIME_TICK = Intent.ACTION_TIME_TICK;

    private AnalogClockView mClockView;
    private Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock_test);

        mClockView = (AnalogClockView) findViewById(R.id.view_clock);
    }

    @Override
    protected void onResume () {
        super.onResume();
        mCalendar = Calendar.getInstance();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_TIME_TICK);
        registerReceiver(mTimeChangeReceiver, filter);
        updateViewTime();
    }

    @Override
    protected void onPause () {
        super.onPause();
        unregisterReceiver(mTimeChangeReceiver);
    }

    private BroadcastReceiver mTimeChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateViewTime();
        }
    };

    private void updateViewTime() {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mClockView.setTime(mCalendar.get(Calendar.HOUR), mCalendar.get(Calendar.MINUTE));
        mClockView.invalidate();
    }
}
