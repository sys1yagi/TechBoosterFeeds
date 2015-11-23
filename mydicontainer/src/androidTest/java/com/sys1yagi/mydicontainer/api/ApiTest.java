package com.sys1yagi.mydicontainer.api;

import com.sys1yagi.mydicontainer.log.Logger;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApiTest {

    @Test
    public void verifyLogger() throws Exception {
        HttpResponse response = mock(HttpResponse.class);
        when(response.status()).thenReturn(200);
        when(response.body()).thenReturn("success");

        HttpRequest request = mock(HttpRequest.class);
        when(request.execute()).thenReturn(response);

        HttpRequestFactory factory = mock(HttpRequestFactory.class);
        when(factory.create(anyString())).thenReturn(request);

        Logger logger = mock(Logger.class);

        Api api = new Api(factory, logger);
        String result = api.request("test");

        assertThat(result, is("success"));
        verify(logger).log(eq("log"), eq("url = test statusCode = 200"));
    }
}
