package org.example.ch08.ex04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    비공정락이 훨씬 빠름

출력)
    비공정 락의 실행 시간: 33밀리초
    공정 락의 실행 시간: 1890밀리초
 */

public class LockFairnessPerformanceExample {
    private static final int THREAD_COUNT = 5;
    private static final int ITERATIONS = 100_000;
    private static final Lock fairLock = new ReentrantLock(true);
    private static final Lock unfairLock = new ReentrantLock(false);

    public static void main(String[] args) {
        runTest("비공정 락", unfairLock);
        runTest("공정 락", fairLock);
    }

    private static void runTest(String lockType, Lock lock) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    lock.lock();

                    try {
                        // 자원에 대한 작업 수행
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println(lockType + "의 실행 시간: " + elapsedTime + "밀리초");
    }
}
