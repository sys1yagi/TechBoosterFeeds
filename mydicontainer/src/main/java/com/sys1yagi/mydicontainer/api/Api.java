package com.sys1yagi.mydicontainer.api;

import com.sys1yagi.mydicontainer.log.Logger;

public class Api {

    Logger logger;

    HttpRequestFactory factory;

    public Api(HttpRequestFactory factory, Logger logger) {
        this.factory = factory;
        this.logger = logger;
    }

    public String request(String url) {
        HttpRequest request = factory.create(url);
        HttpResponse response = request.execute();
        int statusCode = response.status();

        logger.log("log", "url = " + url + " statusCode = " + statusCode);
        //...
        return response.body();
    }
}
