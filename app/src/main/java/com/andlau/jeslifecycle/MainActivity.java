package com.andlau.jeslifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.andlau.jeslifecycle.net.JesNetListener;
import com.andlau.jeslifecycle.net.JesNetLoader;
import com.andlau.jeslifecyclelib.JesLifeCycle;
import com.andlau.jeslifecyclelib.JesLifeCycleOwner;

public class MainActivity extends AppCompatActivity implements JesLifeCycleOwner {

    private static final String TAG = MainActivity.class.getSimpleName();
    private JesLifeCycle mJesLifeCycle;
    private TextView mTvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_result);
        mJesLifeCycle = new JesLifeCycle();
        mJesLifeCycle.inject(this);
    }

    public void get(View view) {
        JesNetLoader jesNetLoader = new JesNetLoader();
        jesNetLoader.registerJesLifeCycle(this);
        jesNetLoader.setJesNetListener(new JesNetListener() {
            @Override
            public void success() {
                Log.d(TAG, "网络请求成功，更新UI");
                mTvResult.setText("网络请求成功");
            }

            @Override
            public void failed() {
                Log.d(TAG, "网络请求失败，更新UI");
                mTvResult.setText("网络请求失败");
            }
        }).get();
    }

    @Override
    public JesLifeCycle getJesLifeCycle() {
        return mJesLifeCycle;
    }
}
