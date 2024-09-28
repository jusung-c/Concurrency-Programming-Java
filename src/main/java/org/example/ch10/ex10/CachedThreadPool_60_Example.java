package org.example.ch10.ex10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool_60_Example {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is executing on " + Thread.currentThread().getName());
            });
        }

        // 60초동안 아무 작업도 수행하지 않음
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 60초 이상동안 사용되지 않은 스레드는 자동 종료됨
        executor.shutdown();
    }
}
