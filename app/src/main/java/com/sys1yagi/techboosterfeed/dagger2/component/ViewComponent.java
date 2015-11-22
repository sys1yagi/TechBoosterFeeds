package com.sys1yagi.techboosterfeed.dagger2.component;

import com.sys1yagi.techboosterfeed.dagger2.module.ViewModule;
import com.sys1yagi.techboosterfeed.view.FeedAdapter;

import dagger.Component;

@Component(modules = {ViewModule.class})
public interface ViewComponent {

    FeedAdapter provideFeedAdapter();
}
