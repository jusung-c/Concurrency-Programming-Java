package org.example.ch10.ex08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = new ArrayList<>();

        tasks.add(() -> {
            throw new RuntimeException("invokeAll");
        });

        tasks.add(() -> {
            Thread.sleep(3000);
            return 1;
        });

        tasks.add(() -> {
            Thread.sleep(2000);
            return 1;
        });

        long started = 0;

        try {
            started = System.currentTimeMillis();
            List<Future<Integer>> results = executorService.invokeAll(tasks);

            for (Future<Integer> future : results) {
                try {
                    Integer value = future.get();
                    System.out.println("value = " + value);
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();

                    if (cause instanceof RuntimeException) {
                        System.out.println("exception: " + cause.getMessage());
                    } else {
                        e.printStackTrace();
                    }
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        System.out.println("총 소요시간:"  + (System.currentTimeMillis() - started ));
    }
}
