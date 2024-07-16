package org.example.ch06.ex04;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockVsSynchronized {
    private AtomicBoolean spinLock = new AtomicBoolean(false);
    private final Object syncLock = new Object();
    private int count = 0;

    final static int THREAD_COUNT = 10_000;
    final int ITERATIONS = 10_000;

    public void useSpinLock() {
        // 스핀락으로 락 획득 계속 시도
        while (!spinLock.compareAndSet(false, true)) ;

        // Critical Section
        for (int j = 0; j < ITERATIONS; j++) {
            count++;
        }

        // 락 해제
        spinLock.set(false);
    }

    public void useSynchronized() {
        synchronized (syncLock) {
            for (int j = 0; j < ITERATIONS; j++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockVsSynchronized tester = new SpinLockVsSynchronized();

        // synchronized 성능 테스트
        Thread[] syncThreads = new Thread[THREAD_COUNT];
        long start2 = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(tester::useSynchronized);

            syncThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].join();
        }

        long end2 = System.currentTimeMillis();
        System.out.println("synchronized 시간: " + (end2 - start2));

        // spinLock 성능 테스트
        Thread[] spinThreads = new Thread[THREAD_COUNT];
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            spinThreads[i] = new Thread(tester::useSpinLock);
            spinThreads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            spinThreads[i].join();
        }
        long end = System.currentTimeMillis();
        System.out.println("스핀락 시간: " + (end - start));
    }
}
/*
스레드 5개, 반복 10만번 -> syncronized가 빠름
    synchronized 시간: 16
    스핀락 시간: 31

스레드 500개 반복 10만번 -> syncronized가 빠름
    synchronized 시간: 107
    스핀락 시간: 2170

스레드 만개, 반복 만번 -> spinLock이 빠름
    synchronized 시간: 1190
    스핀락 시간: 543

스레드 5개, 반복 100번 -> spinLock이 빠름
    synchronized 시간: 2
    스핀락 시간: 1
 */