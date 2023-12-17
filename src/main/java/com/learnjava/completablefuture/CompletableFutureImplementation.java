package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

/**
 * Completable Future Implementation.
 */
public class CompletableFutureImplementation {

    public static void main(String[] args) {

        HelloWorldService hws = new HelloWorldService();

        CompletableFuture.supplyAsync(() -> hws.helloWorld())
                .thenAccept((result) -> {
                    log("Result is " + result);
                }).join(); // will block the main thread, till we get the result.
        log("Done!");
    }
}
