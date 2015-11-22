package com.sys1yagi.introductionofdi.log;

import org.apache.commons.io.FileUtils;

import android.content.Context;

import java.io.File;
import java.io.IOException;

public class FileLogger implements Logger {

    Context context;

    File file;

    public FileLogger(Context context) {
        this.context = context;
        this.file = new File(context.getCacheDir(), "log.txt");
    }

    @Override
    public void log(String tag, String message) {
        try {
            FileUtils.write(file, tag + ":" + message + "\n", true);
        } catch (IOException e) {
            throw new IllegalStateException("failed write log", e);
        }
    }
}
