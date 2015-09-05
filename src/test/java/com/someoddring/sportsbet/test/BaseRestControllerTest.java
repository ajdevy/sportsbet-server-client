package com.someoddring.sportsbet.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.nio.charset.Charset;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseRestControllerTest {
    protected static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );


    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    protected abstract Object createController();

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(createController())
                .setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver())
                .build();
    }

}
