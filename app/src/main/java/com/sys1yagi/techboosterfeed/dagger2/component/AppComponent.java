package com.sys1yagi.techboosterfeed.dagger2.component;

import com.squareup.okhttp.OkHttpClient;
import com.sys1yagi.techboosterfeed.api.FeedLoader;
import com.sys1yagi.techboosterfeed.dagger2.module.AppModule;
import com.sys1yagi.techboosterfeed.log.Logger;

import org.jsoup.parser.Parser;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Logger provideLogger();

    OkHttpClient provideOkHttpClient();

    FeedLoader provideFeedLoader();

    Parser provideParser();
}
