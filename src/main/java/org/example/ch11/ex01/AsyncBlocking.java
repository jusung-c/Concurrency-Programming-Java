package org.example.ch11.ex01;

import java.util.concurrent.*;

public class AsyncBlocking {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Hello Java";
        };

        // 비동기 & 논블록킹
        Future<String> future = executor.submit(task);

        // 블로킹
        try {
            String result = future.get();
            System.out.println("블로킹 작업 결과: " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // 메인 작업 수행
        System.out.println("메인 작업 시작");
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);

        executor.shutdown();
    }
}
