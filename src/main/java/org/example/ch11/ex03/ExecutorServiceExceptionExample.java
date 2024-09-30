package org.example.ch11.ex03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExceptionExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executor.submit(() -> 2);
        Future<Integer> future2 = executor.submit(() -> {
//            return future1.get() + 3;
            throw new RuntimeException("error");
        });

        int result;
        try {
            result = future2.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("예외 발생: " + e.getMessage());
            result = 0; // 예외가 발생하면 0 반환
        }

        int finalResult = result;
        System.out.println("finalResult = " + finalResult);

        executor.shutdown();
    }
}
