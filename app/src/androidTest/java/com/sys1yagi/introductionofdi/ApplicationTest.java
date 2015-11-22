package com.sys1yagi.introductionofdi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void app() {
        assertThat("2", is("2"));
    }
}
