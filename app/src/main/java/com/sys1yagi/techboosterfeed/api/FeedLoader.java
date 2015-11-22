package com.sys1yagi.techboosterfeed.api;

import com.sys1yagi.techboosterfeed.model.Feed;

import java.util.List;

import rx.Observable;

public interface FeedLoader {

    Observable<List<Feed>> get(String url);
}
