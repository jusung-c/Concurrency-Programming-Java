package org.example.ch10.ex10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}
