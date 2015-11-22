package com.sys1yagi.techboosterfeed.dagger2.module;

import com.squareup.okhttp.OkHttpClient;
import com.sys1yagi.techboosterfeed.BuildConfig;
import com.sys1yagi.techboosterfeed.api.FeedLoader;
import com.sys1yagi.techboosterfeed.api.OkHttpFeedLoader;
import com.sys1yagi.techboosterfeed.log.FileLogger;
import com.sys1yagi.techboosterfeed.log.Logger;
import com.sys1yagi.techboosterfeed.log.NoOpLogger;

import org.jsoup.parser.Parser;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    public FeedLoader provideFeedLoader(OkHttpClient client, Parser parser) {
        return new OkHttpFeedLoader(client, parser);
    }

    @Singleton
    @Provides
    public Parser provideParser() {
        return Parser.xmlParser();
    }

    @Singleton
    @Provides
    public Logger provideLogger() {
        if (BuildConfig.DEBUG) {
            return new FileLogger(application);
        } else {
            return new NoOpLogger();
        }
    }
}
