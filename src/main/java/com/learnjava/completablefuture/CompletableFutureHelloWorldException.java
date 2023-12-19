package com.learnjava.completablefuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.LoggerUtil.log;

import com.learnjava.service.HelloWorldService;
import java.util.concurrent.CompletableFuture;
public class CompletableFutureHelloWorldException {

    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {this.hws = hws;}

    public String helloworld_three_async_calls_withExceptionHandling() {
        stopWatchReset();
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> " Hi Completable Future!");

        String hw = hello
                .handle((res, e) -> {
                    if(e == null){
                        log("Result : " + res);
                        return res;
                    } else {
                        log("Exception for hello is : " + e.getMessage());
                        return "";
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((res, e) -> {
                    if(e == null){
                        log("Result : " + res);
                        return res;
                    } else {
                        log("Exception for world is : " + e.getMessage());
                        return "";
                    }
                })
                .thenCombine(greeting, (previous, current) -> previous + current )
                .thenApply(String::toUpperCase).join();
        timeTaken();
        return hw;
    }

    public String helloworld_three_async_calls_exceptionally() {
        stopWatchReset();
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> greeting = CompletableFuture.supplyAsync(() -> " Hi Completable Future!");

        String hw = hello
                .exceptionally((e) -> {
                    log("Exception for hello is : " + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally((e) -> {
                    log("Exception for world is : " + e.getMessage());
                    return "";
                })
                .thenCombine(greeting, (previous, current) -> previous + current )
                .thenApply(String::toUpperCase).join();
        timeTaken();
        return hw;
    }
}
