package com.sys1yagi.introductionofdi.api;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.sys1yagi.introductionofdi.dagger2.component.AppComponent;
import com.sys1yagi.introductionofdi.dagger2.component.DaggerAppComponent;
import com.sys1yagi.introductionofdi.dagger2.module.AppModule;
import com.sys1yagi.introductionofdi.model.Feed;
import com.sys1yagi.introductionofdi.test.AssetsLoader;
import com.sys1yagi.introductionofdi.utils.ListUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.app.Application;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Response.class, ResponseBody.class})
public class OkHttpFeedLoaderTest {

    OkHttpFeedLoader feedLoader;

    OkHttpClient mockedClient;

    class TestModule extends AppModule {

        public TestModule(Application application) {
            super(application);
        }

        @Override
        public OkHttpClient provideOkHttpClient() {
            return mock(OkHttpClient.class);
        }
    }

    @Before
    public void setUp() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new TestModule(null))
                .build();
        mockedClient = appComponent.provideOkHttpClient();
        feedLoader = (OkHttpFeedLoader) appComponent.provideFeedLoader();
    }

    @Test
    public void xml2Feed() throws Exception {
        List<Feed> feeds = feedLoader.xml2Feed("http://test", AssetsLoader.loadFromAssets(
                "dummy_feed.xml"));
        assertThat(feeds, is(notNullValue()));
        assertThat(feeds.size(), is(10));

        Feed feed = ListUtils.head(feeds);
        assertThat(feed.getTitle(), is("techbooster.fm 第9回 「やんざむのコミケでかく内容とアウトプットのはなし」"));
        assertThat(feed.getCategories(), is(notNullValue()));
        assertThat(feed.getDescription(),
                is("アイキャッチ 第10回「事後」 PODCAST : 今回の内容！ Rebuild.fmなど、技術情報をざっくばらんにお伝えするpodcastいい感じですよね！ というわけでtechboosterでも試しに初めて見ることに [&#8230;]"));
        assertThat(feed.getPubDate(), is("Thu, 19 Nov 2015 23:00:50 +0000"));
        assertThat(feed.getLink(), is("http://techbooster.org/podcast/17276/"));
        assertThat(feed.getComments(), is("http://techbooster.org/podcast/17276/#comments"));
        assertThat(feed.getCategories().size(), is(1));
        assertThat(feed.getCategories().get(0), is("Podcast"));
    }

    //mockwebserverを使ってもできる
    //https://github.com/square/okhttp/tree/master/mockwebserver
    @Test
    public void loadSuccess() throws Exception {
        Call call = mock(Call.class);
        when(mockedClient.newCall(any(Request.class))).thenReturn(call);

        Response response = mock(Response.class);
        ResponseBody body = mock(ResponseBody.class);
        when(body.string()).thenReturn(AssetsLoader.loadFromAssets("dummy_feed.xml"));
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(body);

        when(call.execute()).thenReturn(response);

        assertThat(feedLoader, is(notNullValue()));

        TestSubscriber<List<Feed>> testSubscriber = new TestSubscriber<>();

        feedLoader.get("http://test").subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(3, TimeUnit.SECONDS);
        testSubscriber.assertNoErrors();

        List<Feed> feeds = ListUtils.head(testSubscriber.getOnNextEvents());
        //System.out.println(feeds);
        assertThat(feeds, is(notNullValue()));
    }
}
