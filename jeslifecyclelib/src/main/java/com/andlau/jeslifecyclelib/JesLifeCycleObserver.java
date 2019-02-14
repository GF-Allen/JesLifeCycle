package com.andlau.jeslifecyclelib;

/**
 * @Description:
 * @Author: Lxq
 * @Date: 2019/2/14
 */
public interface JesLifeCycleObserver {

    void registerJesLifeCycle(JesLifeCycleOwner jesLifeCycleOwner);

    void unRegisterJesLifeCycle();

    void dispatchEvent(JesLifeCycle.Event event);
}
