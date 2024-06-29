package org.example.ch01.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundExample {
    public static void main(String[] args) {
        // IO 바운드는 쓰레드 개수를 CPU 수보다 크게 해야 IO 때문에 스레드가 대기 상태에 진입했을 때 노는 CPU가 생기지 않는다.
        int numThreads = Runtime.getRuntime().availableProcessors() * 2; // 16개 쓰레드
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {

                    // IO 가 집중 되는 작업
                    for (int j = 0; j < 5; j++) {
                        Files.readAllLines(Path.of("/Users/jus/dev/java/Concurrency-Programming-Java/src/main/java/org/example/ch01/ex03/sample.txt"));
                        System.out.println("스레드: " + Thread.currentThread().getName() +", " +j); // IO Bound 일때 ContextSwitching 이 일어난다
                    }

                    // 아주 빠른 Cpu 연산
                    int result = 0;
                    for (long j = 0; j < 10; j++) {
                        result += j;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}

/*
        스레드: pool-1-thread-2, 0
        스레드: pool-1-thread-13, 0
        스레드: pool-1-thread-16, 0
        스레드: pool-1-thread-12, 0
        스레드: pool-1-thread-8, 0
        스레드: pool-1-thread-12, 1
        스레드: pool-1-thread-8, 1
        스레드: pool-1-thread-12, 2
        스레드: pool-1-thread-8, 2
        스레드: pool-1-thread-12, 3
        스레드: pool-1-thread-8, 3
        스레드: pool-1-thread-12, 4
        스레드: pool-1-thread-8, 4
        스레드: pool-1-thread-5, 0
        스레드: pool-1-thread-9, 0
        스레드: pool-1-thread-1, 0
        스레드: pool-1-thread-5, 1
        스레드: pool-1-thread-1, 1
        스레드: pool-1-thread-15, 0
        스레드: pool-1-thread-10, 0
        스레드: pool-1-thread-5, 2
        스레드: pool-1-thread-9, 1
        스레드: pool-1-thread-10, 1
        스레드: pool-1-thread-9, 2
        스레드: pool-1-thread-7, 0
        스레드: pool-1-thread-9, 3
        스레드: pool-1-thread-7, 1
        스레드: pool-1-thread-6, 0
        스레드: pool-1-thread-7, 2
        스레드: pool-1-thread-9, 4
        스레드: pool-1-thread-7, 3
        스레드: pool-1-thread-10, 2
        스레드: pool-1-thread-5, 3
        스레드: pool-1-thread-7, 4
        스레드: pool-1-thread-15, 1
        스레드: pool-1-thread-10, 3
        스레드: pool-1-thread-1, 2
        스레드: pool-1-thread-5, 4
        스레드: pool-1-thread-10, 4
        스레드: pool-1-thread-14, 0
        스레드: pool-1-thread-1, 3
        스레드: pool-1-thread-4, 0
        스레드: pool-1-thread-3, 0
        스레드: pool-1-thread-1, 4
        스레드: pool-1-thread-11, 0
        스레드: pool-1-thread-16, 1
        스레드: pool-1-thread-13, 1
        스레드: pool-1-thread-16, 2
        스레드: pool-1-thread-11, 1
        스레드: pool-1-thread-13, 2
        스레드: pool-1-thread-2, 1
        스레드: pool-1-thread-4, 1
        스레드: pool-1-thread-13, 3
        스레드: pool-1-thread-16, 3
        스레드: pool-1-thread-2, 2
        스레드: pool-1-thread-13, 4
        스레드: pool-1-thread-4, 2
        스레드: pool-1-thread-16, 4
        스레드: pool-1-thread-2, 3
        스레드: pool-1-thread-4, 3
        스레드: pool-1-thread-2, 4
        스레드: pool-1-thread-3, 1
        스레드: pool-1-thread-14, 1
        스레드: pool-1-thread-3, 2
        스레드: pool-1-thread-14, 2
        스레드: pool-1-thread-3, 3
        스레드: pool-1-thread-15, 2
        스레드: pool-1-thread-6, 1
        스레드: pool-1-thread-15, 3
        스레드: pool-1-thread-14, 3
        스레드: pool-1-thread-6, 2
        스레드: pool-1-thread-3, 4
        스레드: pool-1-thread-14, 4
        스레드: pool-1-thread-4, 4
        스레드: pool-1-thread-11, 2
        스레드: pool-1-thread-6, 3
        스레드: pool-1-thread-15, 4
        스레드: pool-1-thread-11, 3
        스레드: pool-1-thread-6, 4
        스레드: pool-1-thread-11, 4
 */
