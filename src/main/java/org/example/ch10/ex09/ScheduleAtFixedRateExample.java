package org.example.ch10.ex09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // 1초를 delay로 가지는데, 이전 작업이 끝난 뒤에 delay가 시작된다.
        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(true);
        scheduler.shutdown();
    }
}
