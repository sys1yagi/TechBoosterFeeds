package com.sys1yagi.techboosterfeed;

import com.facebook.stetho.Stetho;
import com.sys1yagi.techboosterfeed.dagger2.component.AppComponent;
import com.sys1yagi.techboosterfeed.dagger2.component.DaggerAppComponent;
import com.sys1yagi.techboosterfeed.dagger2.module.AppModule;

import android.app.Application;

public class DIApplication extends Application {

    static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        Stetho.initializeWithDefaults(this);
    }

}
