package com.andlau.jeslifecycle.net;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.andlau.jeslifecyclelib.JesLifeCycle;
import com.andlau.jeslifecyclelib.JesLifeCycleObserver;
import com.andlau.jeslifecyclelib.JesLifeCycleOwner;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019/2/14
 */
public class JesNetLoader implements JesLifeCycleObserver {

    private static final String TAG = JesNetLoader.class.getSimpleName();

    private JesNetListener mJesNetListener;
    private JesLifeCycleOwner mJesLifeCycleOwner;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void get() {
        Log.d(TAG, "开始GET请求");
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                dispatchResult();
            }
        }.start();
    }

    public void post() {
        Log.d(TAG, "开始POST请求");
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                dispatchResult();
            }
        }.start();
    }

    private void dispatchResult() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mJesNetListener != null) {
                    mJesNetListener.success();
                }
            }
        });
    }

    public void cancel() {
        mJesNetListener = null;
        unRegisterJesLifeCycle();
    }

    @Override
    public void registerJesLifeCycle(JesLifeCycleOwner jesLifeCycleOwner) {
        this.mJesLifeCycleOwner = jesLifeCycleOwner;
        mJesLifeCycleOwner.getJesLifeCycle().addObserver(this);
    }

    @Override
    public void unRegisterJesLifeCycle() {
        if (mJesLifeCycleOwner != null) {
            mJesLifeCycleOwner.getJesLifeCycle().removeObserver(this);
        }
    }

    @Override
    public void dispatchEvent(JesLifeCycle.Event event) {
        if (event.equals(JesLifeCycle.Event.ON_DESTROY)) {
            cancel();
        }
    }

    public JesNetLoader setJesNetListener(JesNetListener jesNetListener) {
        mJesNetListener = jesNetListener;
        return this;
    }
}
