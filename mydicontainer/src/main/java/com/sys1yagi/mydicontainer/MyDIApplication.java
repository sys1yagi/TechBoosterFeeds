package com.sys1yagi.mydicontainer;

import com.sys1yagi.mydicontainer.api.Api;
import com.sys1yagi.mydicontainer.api.HttpRequestFactory;
import com.sys1yagi.mydicontainer.container.DIContainer;
import com.sys1yagi.mydicontainer.container.Provider;
import com.sys1yagi.mydicontainer.log.DebugLogger;
import com.sys1yagi.mydicontainer.log.Logger;

import android.app.Application;

public class MyDIApplication extends Application {

    DIContainer diContainer;

    public DIContainer getDiContainer() {
        return diContainer;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        diContainer = DIContainer.create();
        diContainer.register(Logger.class, new Provider<Logger>() {
            @Override
            public Logger get() {
                return new DebugLogger();
            }
        });
        diContainer.register(HttpRequestFactory.class, new Provider<HttpRequestFactory>() {
            @Override
            public HttpRequestFactory get() {
                return new HttpRequestFactory();
            }
        });
        diContainer.register(Api.class, new Provider<Api>() {
            @Override
            public Api get() {
                return new Api(
                        diContainer.get(HttpRequestFactory.class),
                        diContainer.get(Logger.class)
                );
            }
        });
    }
}
