package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService hws = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloworld_three_async_calls_handle() {
        when(hws.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(hws.world()).thenCallRealMethod();

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!", result);

    }

    @Test
    void helloworld_three_async_calls_handle_2() {
        when(hws.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(hws.world()).thenThrow(new RuntimeException("Exception Occurred"));

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals(" HI COMPLETABLE FUTURE!", result);

    }

    @Test
    void helloworld_three_async_calls_handle_3() {
        when(hws.hello()).thenCallRealMethod();
        when(hws.world()).thenCallRealMethod();

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!", result);

    }

    @Test
    void helloworld_three_async_calls_exceptionally() {
        when(hws.hello()).thenCallRealMethod();
        when(hws.world()).thenCallRealMethod();

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals("HELLO WORLD! HI COMPLETABLE FUTURE!", result);

    }

    @Test
    void helloworld_three_async_calls_exceptionally_2() {
        when(hws.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(hws.world()).thenCallRealMethod();

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!", result);

    }

    @Test
    void helloworld_three_async_calls_exceptionally_3() {
        when(hws.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(hws.world()).thenThrow(new RuntimeException("Exception Occurred"));

        String result = hwcfe.helloworld_three_async_calls_withExceptionHandling();
        // expected value would be "WORLD! HI COMPLETABLEFUTURE!"
        assertEquals(" HI COMPLETABLE FUTURE!", result);

    }


}
