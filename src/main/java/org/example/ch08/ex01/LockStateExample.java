package org.example.ch08.ex01;

import java.util.concurrent.locks.ReentrantLock;

/*
    한 스레드가 동일한 락을 3번 획득하고 2번만 해제하면 다음 스레드는 무한 대기 상태로 빠진다.

 */
public class LockStateExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock.lock(); // 락 획득 (1번)
            try {
                System.out.println("스레드 1이 락을 1번 획득했습니다.");

                lock.lock(); // 락 획득 (2번)
                try {
                    System.out.println("스레드 1이 락을 2번 획득했습니다.");

                    lock.lock(); // 락 획득 (3번)
                    try {
                        System.out.println("스레드 1이 락을 3번 획득했습니다.");
                    } finally {
                        lock.unlock(); // 락 해제 (1번)
                        System.out.println("스레드 1이 락을 1번 해제했습니다.");
                    }
                } finally {
                    lock.unlock(); // 락 해제 (2번)
                    System.out.println("스레드 1이 락을 2번 해제했습니다.");
                }
            } finally {
//                lock.unlock(); // 락 해제 (3번)
                System.out.println("스레드 1이 락을 3번 해제했습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock(); // 락 획득 (스레드 1이 락을 세 번 해제할 때까지 대기)
            try {
                System.out.println("스레드 2가 락을 획득했습니다.");
            } finally {
                lock.unlock(); // 락 해제
                System.out.println("스레드 2가 락을 해제했습니다.");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
    스레드 1이 락을 1번 획득했습니다.
    스레드 1이 락을 2번 획득했습니다.
    스레드 1이 락을 3번 획득했습니다.
    스레드 1이 락을 1번 해제했습니다.
    스레드 1이 락을 2번 해제했습니다.
    스레드 1이 락을 3번 해제했습니다.
    ... 무한 대기
 */