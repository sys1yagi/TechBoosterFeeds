package com.sys1yagi.techboosterfeed.dagger2.component;


import com.sys1yagi.techboosterfeed.activity.MainActivity;
import com.sys1yagi.techboosterfeed.dagger2.module.ActivityModule;
import com.sys1yagi.techboosterfeed.dagger2.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class, ViewComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {


    void inject(MainActivity activity);
}
