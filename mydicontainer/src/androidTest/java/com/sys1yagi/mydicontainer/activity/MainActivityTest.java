package com.sys1yagi.mydicontainer.activity;

import com.sys1yagi.mydicontainer.MyDIApplicationMock;
import com.sys1yagi.mydicontainer.R;
import com.sys1yagi.mydicontainer.api.Api;
import com.sys1yagi.mydicontainer.container.DIContainer;
import com.sys1yagi.mydicontainer.container.Provider;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    MainActivity activity;

    @Test
    public void requestSuccess() throws Exception {
        final Api api = mock(Api.class);
        DIContainer container = new DIContainer();
        container.register(Api.class, new Provider<Api>() {
            @Override
            public Api get() {
                return api;
            }
        });
        ((MyDIApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext())
                .setDiContainer(container);
        when(api.request(anyString())).thenReturn("success!");

        activity = rule.launchActivity(new Intent());
        onView(withId(R.id.text)).check(matches(withText("success!")));
    }
}
