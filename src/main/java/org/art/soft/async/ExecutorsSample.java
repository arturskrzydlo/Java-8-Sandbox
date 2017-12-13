package org.art.soft.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorsSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> someIntegers = Arrays.asList(1, 2, 3, 4, 5);

        List<Future<String>> futures = someIntegers.stream()
                                                   .map(it -> executorService.submit(ExecutorsSample::call))
                                                   .collect(Collectors.toList());
        TimeUnit.SECONDS.sleep(15);

        futures.stream().forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static String call() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread());

        return "test";
    }
}
