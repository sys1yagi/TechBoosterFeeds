package com.sys1yagi.techboosterfeed.dagger2.module;

import com.cookpad.android.rxt4a.subscriptions.AndroidCompositeSubscription;
import com.sys1yagi.techboosterfeed.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    @ActivityScope
    @Provides
    public AndroidCompositeSubscription provideAndroidCompositeSubscription() {
        return new AndroidCompositeSubscription();
    }
}
