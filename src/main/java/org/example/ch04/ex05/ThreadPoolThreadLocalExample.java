package org.example.ch04.ex05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    스레드 풀에서 기존 사용하던 스레드 로컬 값을 삭제해주지 않으면 다음에 재사용될 때
    기존 값을 참조할 수 있으므로 따라서 반드시 삭제해주자
 */
public class ThreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 2개의 스레드를 가진 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 첫 번째 작업: ThreadLocal 값을 설정
        executor.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());

            // 반드시 삭제해주기
            threadLocal.remove();
        });

        // 잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러 번의 두 번째 작업: ThreadLocal 값을 설정하지 않고 바로 값을 가져와 출력
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            });
        }

        executor.shutdown();
    }
}

/*  삭제 전 출력
    pool-1-thread-1 : 작업 1의 값
    pool-1-thread-1 : 작업 1의 값
    pool-1-thread-2 : null
    pool-1-thread-2 : null
    pool-1-thread-1 : 작업 1의 값
    pool-1-thread-2 : null
 */

/*  삭제 후 출력
    pool-1-thread-1 : 작업 1의 값
    pool-1-thread-2 : null
    pool-1-thread-2 : null
    pool-1-thread-1 : null
    pool-1-thread-2 : null
    pool-1-thread-1 : null
 */