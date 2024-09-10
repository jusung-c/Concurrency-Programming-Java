package org.example.ch10.ex06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 제출
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Callable 작업 실행");
            return 42;
        });

        int result = future.get();
        System.out.println("result = " + result);

        executorService.shutdown();
    }
}
