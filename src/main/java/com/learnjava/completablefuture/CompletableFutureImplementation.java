package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;



/**
 * Completable Future Implementation.
 */
public class CompletableFutureImplementation {

    private HelloWorldService hws = new HelloWorldService();

    public CompletableFutureImplementation(HelloWorldService hws) {
        this.hws = hws;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public String helloworld_multiple_async_calls() {
        stopWatchReset();
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());

        String hw = hello
                .thenCombine(world, (h, w) -> h + w)
                .thenApply(String::toUpperCase).join();
        timeTaken();
        return hw;
    }


    public String helloworld_three_async_calls() {
        stopWatchReset();
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> " Hi Completable Future!");

        String hw = hello
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(greeting, (previous, current) -> previous + current )
                .thenApply(String::toUpperCase).join();
        timeTaken();
        return hw;
    }

    public static void main(String[] args) {

        HelloWorldService hws = new HelloWorldService();

        CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase)
                .thenAccept((result) -> {
                    log("Result is " + result);
                }).join(); // will block the main thread, till we get the result.
        log("Done!");
    }
}
