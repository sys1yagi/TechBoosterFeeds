package com.sys1yagi.mydicontainer;

import com.sys1yagi.mydicontainer.container.DIContainer;

import android.util.Log;

public class MyDIApplicationMock extends MyDIApplication {

    @Override
    public void onCreate() {
        Log.d("moge", "soggegee");
        super.onCreate();
    }

    @Override
    public DIContainer getDiContainer() {
        Log.d("moge", "getDiContainer:" + diContainer);
        return super.getDiContainer();
    }

    public void setDiContainer(DIContainer _diContainer) {
        Log.d("moge", "setDiContaienr:" + _diContainer);
        diContainer = _diContainer;
    }
}
