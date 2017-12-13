package org.art.soft.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class AsyncSample {

    public static Integer generate() {

        System.out.println("doing work " + Thread.currentThread());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static void printIt(int value) {

        System.out.println(value + " " + Thread.currentThread());
    }

    public static int processError(Throwable throwable) {

        System.out.println("Error : " + throwable.getMessage());
        throw new RuntimeException(throwable.getMessage());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool pool1 = new ForkJoinPool(8);
        ForkJoinPool pool2 = new ForkJoinPool(8);

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture.supplyAsync(AsyncSample::generate)
                         // .exceptionally(AsyncSample::processError)
                         .thenApply(data -> data * 2)
                         // .exceptionally(AsyncSample::processError)
                         .thenAccept(AsyncSample::printIt);

    /*    ScheduledExecutorService scheduler = ExecutorsSample.newScheduledThreadPool(5);
        CompletableFuture timeoutFuture = new CompletableFuture();
        // Run a scheduled task which runs after 100 milliseconds
        scheduler.schedule(timeoutFuture.completeExceptionally(new TimeoutException()), 100, TimeUnit.MILLISECONDS));
        CompletableFuture future = new CompletableFuture();

        scheduler.schedule(future.completeExceptionally(new TimeoutException()),100L,TimeUnit.MILLISECONDS);*/

        Thread.sleep(20000);

        System.out.println("In main ");
    }
}
