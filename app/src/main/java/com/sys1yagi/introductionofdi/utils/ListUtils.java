package com.sys1yagi.introductionofdi.utils;

import java.util.List;

import rx.Observable;

public class ListUtils {

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> T head(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return Observable.from(list).toBlocking().first();
    }
}
