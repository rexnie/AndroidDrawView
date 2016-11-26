package com.example.android.androiddrawview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BitmapMeshActivity extends AppCompatActivity {
    private static final String TAG = "BitmapMeshActivity";
    private BitmapMeshView mBitmapMeshView;
    private Thread mThread;
    private boolean mThreadRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_mesh);
        mBitmapMeshView = (BitmapMeshView) findViewById(R.id.mesh_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(mThreadRunning) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mBitmapMeshView.post(new Runnable() {
                        @Override
                        public void run() {
                            mBitmapMeshView.invalidate();
                        }
                    });
                }
                Log.d(TAG, "run: thread quit");
            }
        });
        mThreadRunning = true;
        mThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mThreadRunning = false;
        mThread = null;
    }
}
