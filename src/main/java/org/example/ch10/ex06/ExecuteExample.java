package org.example.ch10.ex06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Runnable 실행
        executorService.execute(() -> System.out.println("Runnable 작업 실행.."));

        executorService.shutdown();

        new Thread(() -> System.out.println("Runnable 작업 실행..")).start();
    }
}
