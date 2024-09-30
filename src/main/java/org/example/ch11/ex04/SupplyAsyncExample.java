package org.example.ch11.ex04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SupplyAsyncExample {
    public static void main(String[] args) {
        MyService service = new MyService();

        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작합니다");
            return service.getData();
        });

        List<Integer> result = cf.join();
        result.forEach(System.out::println);

        System.out.println("======================================");

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<List<Integer>> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작합니다");
            return service.getData();
        });

        try {
            List<Integer> result2 = future.get();
            result2.forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드 종료");
    }
}

class MyService {
    public List<Integer> getData(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1,2,3);
    }
}