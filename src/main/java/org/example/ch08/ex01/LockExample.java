package org.example.ch08.ex01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {

        // 명시적으로 락 획득
        lock.lock();

        try {
            count++;
        } finally {
            // 반드시 finally 블록에서 락 해제
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();

        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockExample lockExample = new LockExample();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                lockExample.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                lockExample.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count: " + lockExample.getCount());
    }
}
