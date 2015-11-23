package com.sys1yagi.mydicontainer.factory;

import com.sys1yagi.mydicontainer.api.Api;
import com.sys1yagi.mydicontainer.api.HttpRequestFactory;
import com.sys1yagi.mydicontainer.log.Logger;

public class ApiFactory {

    public static Api create() {
        Logger logger = LoggerFactory.create();
        HttpRequestFactory factory = HttpRequestFactoryFactory.create();
        return new Api(factory, logger);
    }
}
