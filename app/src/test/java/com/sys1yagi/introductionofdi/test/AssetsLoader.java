package com.sys1yagi.introductionofdi.test;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AssetsLoader {

    public static String loadFromAssets(String name) throws IOException {
        return IOUtils.toString(new FileInputStream(new File("assets", name)));
    }
}
