package com.sys1yagi.mydicontainer;

import com.sys1yagi.mydicontainer.container.DIContainer;

public class MyDIApplicationRewriter {

    public static void setDiContainer(DIContainer diContainer) {
        MyDIApplication.diContainer = diContainer;
    }
}
