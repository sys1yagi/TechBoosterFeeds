package com.sys1yagi.techboosterfeed.activity;

import com.cookpad.android.rxt4a.operators.OperatorAddToCompositeSubscription;
import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers;
import com.cookpad.android.rxt4a.subscriptions.AndroidCompositeSubscription;
import com.sys1yagi.techboosterfeed.DIApplication;
import com.sys1yagi.techboosterfeed.R;
import com.sys1yagi.techboosterfeed.api.FeedLoader;
import com.sys1yagi.techboosterfeed.config.Network;
import com.sys1yagi.techboosterfeed.dagger2.component.DaggerActivityComponent;
import com.sys1yagi.techboosterfeed.dagger2.component.DaggerViewComponent;
import com.sys1yagi.techboosterfeed.dagger2.module.ViewModule;
import com.sys1yagi.techboosterfeed.log.Logger;
import com.sys1yagi.techboosterfeed.model.Feed;
import com.sys1yagi.techboosterfeed.view.FeedAdapter;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Inject
    Logger logger;

    @Inject
    FeedLoader feedLoader;

    @Inject
    FeedAdapter adapter;

    ListView listView;

    AndroidCompositeSubscription subscriptions = new AndroidCompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .appComponent(DIApplication.getAppComponent())
                .viewComponent(
                        DaggerViewComponent.builder().viewModule(new ViewModule(this)).build())
                .build()
                .inject(this);
        logger.log(TAG, "onCreate");

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);

        loadFeed();
    }

    @Override
    protected void onDestroy() {
        logger.log(TAG, "onDestroy");
        subscriptions.unsubscribe();
        super.onDestroy();
    }

    void loadFeed() {
        feedLoader.get(Network.TECH_BOOSTER_FEED_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .lift(new OperatorAddToCompositeSubscription<List<Feed>>(subscriptions))
                .subscribe(
                        new Action1<List<Feed>>() {
                            @Override
                            public void call(List<Feed> feeds) {
                                logger.log(TAG, "load success");
                                setupUi(feeds);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                logger.log(TAG, "load error : " + throwable.getMessage());
                            }
                        });
    }

    void setupUi(List<Feed> feeds) {
        adapter.addAll(feeds);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO OpenHelperを作ってinjectionする
                //TODO ServiceConnection
                Feed feed = adapter.getItem(position);
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder()
                        .setToolbarColor(getResources().getColor(R.color.colorPrimary))
                        .setShowTitle(true)
                        .enableUrlBarHiding();
                initShareAction(builder, feed);
                CustomTabsIntent customTabsIntent = builder.build();

                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(feed.getLink()));
            }
        });
    }

    void initShareAction(CustomTabsIntent.Builder builder, Feed feed) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, feed.getLink());
        intent.putExtra(Intent.EXTRA_SUBJECT, feed.getTitle());

        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_NO_CREATE);
        builder.setActionButton(
                BitmapFactory.decodeResource(getResources(),
                        android.R.drawable.ic_menu_share),
                "share",
                pendingIntent
        );
    }
}
