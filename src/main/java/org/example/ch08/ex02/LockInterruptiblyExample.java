package org.example.ch08.ex02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("스레드 1 락 획득 성공");
                } finally {
                    lock.unlock();
                    System.out.println("스레드 1 락 해제");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 1 인터럽트 발생");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("스레드 2 락 획득 성공");
                } finally {
                    lock.unlock();
                    System.out.println("스레드 2 락 해제");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 2 인터럽트 발생");
            }
        });

        t1.start();
        t1.interrupt();
        t2.start();
//        t2.interrupt();

        Thread.sleep(500);
        t1.join();
//        t1.interrupt();  작업 다 끝나고 인터럽트는 의미없음
        t2.join();
    }
}
