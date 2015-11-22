package com.sys1yagi.techboosterfeed.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.sys1yagi.techboosterfeed.model.Feed;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class OkHttpFeedLoader implements FeedLoader {

    OkHttpClient client;

    Parser parser;

    public OkHttpFeedLoader(OkHttpClient client, Parser parser) {
        this.client = client;
        this.parser = parser;
    }

    @Override
    public Observable<List<Feed>> get(final String url) {
        return Observable.create(new Observable.OnSubscribe<List<Feed>>() {
            @Override
            public void call(Subscriber<? super List<Feed>> subscriber) {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request)
                            .execute();
                    if (response.isSuccessful()) {
                        List<Feed> feeds = xml2Feed(url, response.body().string());
                        subscriber.onNext(feeds);
                        subscriber.onCompleted();
                    } else {
                        //TODO
                        subscriber
                                .onError(new Exception(response.code() + ":" + response.message()));
                    }

                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    List<Feed> xml2Feed(String url, String xml) {
        Document document = parser.parseInput(xml, url);
        Elements elements = document.select("item");

        List<Feed> feeds = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element item = elements.get(i);
            Feed feed = new Feed();

            feed.setTitle(item.select("title").text());
            feed.setComments(item.select("comments").text());
            feed.setDescription(item.select("description").text());
            feed.setLink(item.select("link").text());
            feed.setCategories(extractCategories(item));
            //TODO to Date Object
            feed.setPubDate(item.select("pubDate").text());

            feeds.add(feed);
        }
        return feeds;
    }

    List<String> extractCategories(Element item) {
        List<String> categories = new ArrayList<>();
        Elements elements = item.select("category");
        for (int i = 0; i < elements.size(); i++) {
            Element category = elements.get(i);
            categories.add(category.text());
        }
        return categories;
    }
}
