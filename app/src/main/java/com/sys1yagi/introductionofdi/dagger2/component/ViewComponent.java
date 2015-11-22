package com.sys1yagi.introductionofdi.dagger2.component;

import com.sys1yagi.introductionofdi.dagger2.module.ViewModule;
import com.sys1yagi.introductionofdi.view.FeedAdapter;

import dagger.Component;

@Component(modules = {ViewModule.class})
public interface ViewComponent {

    FeedAdapter provideFeedAdapter();
}
