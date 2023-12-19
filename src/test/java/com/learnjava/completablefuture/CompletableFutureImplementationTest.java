package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompletableFutureImplementationTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureImplementation cfi = new CompletableFutureImplementation(hws);


    @Test
    void testHelloworld_multiple_async_calls() {
        String hw = cfi.helloworld_multiple_async_calls();
        assertEquals("HELLO WORLD!", hw);
    }

    @Test
    void testHelloworld_three_async_calls() {
        String hw = cfi.helloworld_three_async_calls();
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!", hw);
    }
}