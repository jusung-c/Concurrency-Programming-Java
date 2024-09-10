package org.example.ch10.ex06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
    result1 = 42
    result2 = null
 */
public class SubmitRunnableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Runnable 작업 제출
        Future<Integer> future1 = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 42);

        System.out.println("비동기 작업 시작..");

        int result1 = future1.get();
        System.out.println("result1 = " + result1);

        Future<?> future2 = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Object result2 = future2.get();
        System.out.println("result2 = " + result2);

        executorService.shutdown();


    }
}
