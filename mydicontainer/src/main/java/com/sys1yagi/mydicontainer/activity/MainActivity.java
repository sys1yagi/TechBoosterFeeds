package com.sys1yagi.mydicontainer.activity;

import com.sys1yagi.mydicontainer.MyDIApplication;
import com.sys1yagi.mydicontainer.R;
import com.sys1yagi.mydicontainer.api.Api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("moge", "onCreate Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDIApplication.getDiContainer().inject(this);

        TextView text = (TextView) findViewById(R.id.text);
        if (api != null) {
            text.setText(api.request("test"));
        } else {
            text.setText("api is null");
        }
    }

    private void loadFeed(Api api) {
        api.request("http://techbooster.org/feed");
    }
}
