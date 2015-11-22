package com.sys1yagi.techboosterfeed.log;

import android.util.Log;

public class LogcatLogger implements Logger {

    @Override
    public void log(String tag, String message) {
        Log.d(tag, message);
    }
}
