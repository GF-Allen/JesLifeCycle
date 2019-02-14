package com.andlau.jeslifecyclelib;

import android.app.Activity;
import android.app.FragmentManager;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019/2/14
 */
public class JesLifeCycle {

    private static final String LIFE_CYCLE_FRAGMENT_TAG = "JES_LIFE_CYCLE_FRAGMENT_TAG";

    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY
    }

    private JesLifeCycleFragment fragment;

    public JesLifeCycle() {
        fragment = new JesLifeCycleFragment();
    }

    public void inject(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        if (manager.findFragmentByTag(LIFE_CYCLE_FRAGMENT_TAG) == null) {
            manager.beginTransaction().add(fragment, LIFE_CYCLE_FRAGMENT_TAG).commit();
            manager.executePendingTransactions();
        }
    }

    public void addObserver(JesLifeCycleObserver observer) {
        fragment.addObserver(observer);
    }

    public void removeObserver(JesLifeCycleObserver observer) {
        fragment.removeObserver(observer);
    }


}
