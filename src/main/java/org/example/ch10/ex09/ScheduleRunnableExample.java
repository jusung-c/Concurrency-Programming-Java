package org.example.ch10.ex09;

import java.util.concurrent.*;

public class ScheduleRunnableExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업이 한번 실행됩니다.");
        };

        ScheduledFuture<?> future = scheduler.schedule(task, 2, TimeUnit.SECONDS);

//        try {
//            Object o = future.get();
//            System.out.println("o = " + o);
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
