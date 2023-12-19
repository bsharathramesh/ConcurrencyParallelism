package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    // ForkJoinPool is shared by Parallel Streams, CompletableFuture.

    public String helloworld_three_async_calls_threadpool() {
        stopWatchReset();
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> " Hi Completable Future!");

        String hw = hello
                .thenCombine(world, (h, w) -> {
                    log("First Combine Operations !!");
                    return h + w;
                })
                .thenCombine(greeting, (previous, current) -> {
                    log("Second Combine Operations !!");
                    return previous + current;
                })
                .thenApply( s -> {
                    log("Third operations toUpperCase !!");
                    return s.toUpperCase();
                }).join();
        timeTaken();
        return hw;
    }

    public String helloworld_three_async_calls_custom_threadpool() {
        stopWatchReset();
        startTimer();

        //Executors
        ExecutorService executorService = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello(), executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world(), executorService);
        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> " Hi Completable Future!",
                executorService);

        String hw = hello
                .thenCombine(world, (h, w) -> {
                    log("First Combine Operations !!");
                    return h + w;
                })
                .thenCombine(greeting, (previous, current) -> {
                    log("Second Combine Operations !!");
                    return previous + current;
                })
                .thenApply( s -> {
                    log("Third operations toUpperCase !!");
                    return s.toUpperCase();
                }).join();
        timeTaken();
        return hw;
    }

    public CompletableFuture<String> hello_world_thenCompose() {
        return CompletableFuture.supplyAsync(hws::hello)
                .thenCompose((previous) -> hws.worldFuture(previous))
                .thenApply(String::toUpperCase);
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
