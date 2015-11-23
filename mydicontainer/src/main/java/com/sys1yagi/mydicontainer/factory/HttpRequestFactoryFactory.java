package com.sys1yagi.mydicontainer.factory;

import com.sys1yagi.mydicontainer.api.HttpRequestFactory;

public class HttpRequestFactoryFactory {

    public static HttpRequestFactory create() {
        return new HttpRequestFactory();
    }
}
