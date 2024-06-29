package org.example.ch01.ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBoundExample {
    public static void main(String[] args) {

        int numThreads = Runtime.getRuntime().availableProcessors();    // 8
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads); // 스레드 생성기

        long startTime = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>();    // 스레드 각 작업의 결과를 담을 클래스

        for (int i = 0; i < numThreads; i++) {
            Future<?> future = executorService.submit(() -> {

                // CPU 연산이 집중되고 오래 걸리는 작업
                long result = 0;
                for (long j = 0; j < 1000000000L; j++) {
                    result += j;
                }

                // 잠깐 대기
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("스레드: " + Thread.currentThread().getName() + ", " + result);
            });

            futures.add(future);
        }

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime - startTime) + "ms");
        executorService.shutdown();

        /* 출력 결과
            스레드: pool-1-thread-5, 499999999500000000
            스레드: pool-1-thread-2, 499999999500000000
            스레드: pool-1-thread-7, 499999999500000000
            스레드: pool-1-thread-3, 499999999500000000
            스레드: pool-1-thread-4, 499999999500000000
            스레드: pool-1-thread-1, 499999999500000000
            스레드: pool-1-thread-8, 499999999500000000
            스레드: pool-1-thread-6, 499999999500000000
            CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: 1420ms
         */

    }
}
