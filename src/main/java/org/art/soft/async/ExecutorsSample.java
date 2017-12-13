package org.art.soft.async;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutorsSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

/*        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));*/

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = Stream.generate(() -> executorService.submit(ExecutorsSample::call))
                                             .limit(5)
                                             .collect(Collectors.toList());

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        TimeUnit.SECONDS.sleep(8);

        futures.stream().forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Multithreading rulezz !"), 100, 1000,
                TimeUnit.MILLISECONDS);



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
