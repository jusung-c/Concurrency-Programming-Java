package org.example.ch08.ex02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 1번 스레드가 먼저 락을 획득하면 3초간 들고 있기 때문에 2초동안 시도하는 스레드 2는 락을 획득하지 못한다.
public class TryLockWithTimeoutExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("스레드 1이 락을 획득했습니다.");
                        Thread.sleep(3000);  // 3초동안 보유
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 1이 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 1이 락 획득에 실패했습니다.");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 1이 인터럽트를 받았습니다.");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
//                if (lock.tryLock(4, TimeUnit.SECONDS)) {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("스레드 2가 락을 획득했습니다.");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 2가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 2가 락 획득에 실패했습니다.");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드 2가 인터럽트를 받았습니다.");
            }
        });

        t1.start();
        t2.start();
    }
}
/*
    스레드 1이 락을 획득했습니다.
    스레드 2가 락 획득에 실패했습니다.
    스레드 1이 락을 해제했습니다.
 */