package org.example.ch11.ex03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableTask {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletableFuture<Integer> future = new CompletableFuture<>();

        executor.submit(() -> {
            // 결과 조작하고 즉시 완료시킴
            future.complete(performTask());
        });

        try {
            int result = future.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }

    private static int performTask() {
        return  5;
    }
}
