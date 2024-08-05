package org.example.ch08.ex01;

import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            // 1번 락 획득
            lock1.lock();

            try {
                System.out.println("스레드가 1번 락을 획득했습니다.");

                // 2번 락 획득
                lock2.lock();

                try {
                    System.out.println("스레드가 2번 락을 획득했습니다.");
                } finally {

                    // 1번 락 해제 -> synchronized에선 안됐다. 반드시 락 획득 역순으로 해제해야 했음
                    lock1.unlock();
                    System.out.println("스레드가 1번 락을 해제했습니다.");
                }
            } finally {
                lock2.unlock();
                System.out.println("스레드가 2번 락을 해제했습니다.");
            }
        });

        thread.start();
        thread.join();
    }
}

/*  출력
    스레드가 1번 락을 획득했습니다.
    스레드가 2번 락을 획득했습니다.
    스레드가 1번 락을 해제했습니다.
    스레드가 2번 락을 해제했습니다.
 */