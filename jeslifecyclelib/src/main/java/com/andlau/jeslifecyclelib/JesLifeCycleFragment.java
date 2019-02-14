package com.andlau.jeslifecyclelib;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019/2/14
 */
public class JesLifeCycleFragment extends Fragment {


    private static final String TAG = JesLifeCycleFragment.class.getSimpleName();

    private Set<JesLifeCycleObserver> mJesLifeCycleObservers = new CopyOnWriteArraySet<>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dispatch(JesLifeCycle.Event.ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        dispatch(JesLifeCycle.Event.ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();
        dispatch(JesLifeCycle.Event.ON_RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        dispatch(JesLifeCycle.Event.ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        dispatch(JesLifeCycle.Event.ON_STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispatch(JesLifeCycle.Event.ON_DESTROY);
    }

    private void dispatch(JesLifeCycle.Event event) {
        if (mJesLifeCycleObservers != null) {
            for (JesLifeCycleObserver observer : mJesLifeCycleObservers) {
                observer.dispatchEvent(event);
            }
        }
    }

    public synchronized void addObserver(JesLifeCycleObserver observer) {
        mJesLifeCycleObservers.add(observer);
    }

    public void removeObserver(JesLifeCycleObserver observer) {
        mJesLifeCycleObservers.remove(observer);
    }
}
