package com.sys1yagi.introductionofdi.api;

import com.sys1yagi.introductionofdi.model.Feed;

import java.util.List;

import rx.Observable;

public interface FeedLoader {

    Observable<List<Feed>> get(String url);
}
