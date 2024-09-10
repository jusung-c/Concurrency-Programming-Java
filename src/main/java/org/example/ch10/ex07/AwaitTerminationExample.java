package org.example.ch10.ex07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class AwaitTerminationExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.submit(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " : 데몬 스레드 실행 중..");
                Thread.sleep(1000);
            }
        });

        executorService.shutdown();

        Thread.sleep(300);
        System.out.println("메인 스레드 종료");
    }
}
