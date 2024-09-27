package org.example.ch10.ex09;

import java.security.PrivilegedAction;
import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업이 한번 실행되고 결과를 반환합니다.";
        };

        ScheduledFuture<String> future = scheduler.schedule(task, 3, TimeUnit.SECONDS);

        try {
            System.out.println("future.isDone() = " + future.isDone());
            String result = future.get();
            System.out.println("result = " + result);
            System.out.println("future.isDone() = " + future.isDone());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        scheduler.shutdown();
    }
}
