## JesLifeCycle

> 生命周期感知

### 使用方法
1. 在Activity或Fragment中，初始化一个感知器并注入

        mJesLifeCycle = new JesLifeCycle();
        mJesLifeCycle.inject(this);

2. Activity或者Fragment实现JesLifeCycleOwner成为需感知的对象

        @Override
        public JesLifeCycle getJesLifeCycle() {
            return mJesLifeCycle;
        }

3. 需要感知生命周期的操作，称之为生命周期的观察者，并实现注册到感知器，例如发起一个网络请求，在onDestroy的时候就取消网络加载，只需要实现JesLifeCycleObserver接口，并根据业务处理dispatchEvent

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

4. 在外部调用registerJesLifeCycle，将其注册到当前感知器内

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