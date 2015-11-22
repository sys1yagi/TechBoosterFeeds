package com.sys1yagi.introductionofdi.dagger2.component;


import com.sys1yagi.introductionofdi.activity.MainActivity;
import com.sys1yagi.introductionofdi.dagger2.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class, ViewComponent.class})
public interface ActivityComponent extends AppComponent {


    void inject(MainActivity activity);
}
