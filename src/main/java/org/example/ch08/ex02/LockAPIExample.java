package org.example.ch08.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class LockAPIExample {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                // 스레드 1이 락을 보유한 상태에서 1초 대기
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                // 스레드 2가 락을 보유한 상태에서 1초 대기
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lock에 대한 정보 출력 - 모니터링
        System.out.println("Hold Count: " + lock.getHoldCount());
        System.out.println("Is Held By Current Thread: " + lock.isHeldByCurrentThread());
        System.out.println("Has Queued Threads: " + lock.hasQueuedThreads());
        System.out.println("has Queued Thread1: " + lock.hasQueuedThread(t1));
        System.out.println("has Queued Thread2: " + lock.hasQueuedThread(t2));
        System.out.println("Queue Length: " + lock.getQueueLength());
        System.out.println("isFair: " + lock.isFair());
    }
}
