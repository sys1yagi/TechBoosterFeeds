package com.sys1yagi.mydicontainer.factory;


import com.sys1yagi.mydicontainer.log.DebugLogger;
import com.sys1yagi.mydicontainer.log.Logger;

public class LoggerFactory {

    public static Logger create() {
        return new DebugLogger();
    }
}
