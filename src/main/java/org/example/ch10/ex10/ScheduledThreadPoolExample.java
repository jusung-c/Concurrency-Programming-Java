package org.example.ch10.ex10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Task is running..");
        };

        int initialDelay = 0;   // 초기 지연
        int initialPeriod = 1;  // 초기 주기
        int updatedPeriod = 3;  // 변경된 주기

        // 초기 스케줄링
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, initialPeriod, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000); // 5초간 실행
            future.cancel(true);    // 스케줄링 취소

            System.out.println("변경된 주기로 다시 스케줄링합니다.");
            // 변경된 주기로 다시 스케줄링
            executor.scheduleAtFixedRate(task, 0, updatedPeriod, TimeUnit.SECONDS);

            Thread.sleep(10000); // 10초간 실행
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(false);
        executor.shutdown();
    }
}
