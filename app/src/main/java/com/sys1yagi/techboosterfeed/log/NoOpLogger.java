package com.sys1yagi.techboosterfeed.log;

public class NoOpLogger implements Logger {

    @Override
    public void log(String tag, String message) {
        //no op
    }
}
