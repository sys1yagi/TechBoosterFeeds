package com.sys1yagi.techboosterfeed.dagger2.module;

import com.sys1yagi.techboosterfeed.view.FeedAdapter;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    Context context;

    public ViewModule(Context context) {
        this.context = context;
    }

    @Provides
    public FeedAdapter provideFeedAdapter() {
        return new FeedAdapter(context);
    }
}
