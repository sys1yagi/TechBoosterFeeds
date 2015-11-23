package com.sys1yagi.mydicontainer.activity;

import com.sys1yagi.mydicontainer.MyDIApplication;
import com.sys1yagi.mydicontainer.R;
import com.sys1yagi.mydicontainer.api.Api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyDIApplication) getApplication()).getDiContainer().inject(this);

        TextView text = (TextView) findViewById(R.id.text);
        if (api != null) {
            text.setText(api.request("http://techbooster.org/feed"));
        } else {
            text.setText("api is null");
        }
    }
}
