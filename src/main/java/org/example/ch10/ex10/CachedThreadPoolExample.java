package org.example.ch10.ex10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= 20; i++) {
            executor.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
