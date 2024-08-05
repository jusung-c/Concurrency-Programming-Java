package org.example.ch08.ex02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            boolean acquired = false;

            while (!acquired) {

                // 락 획득 시도
                acquired = lock.tryLock();

                if (acquired) {
                    try {
                        System.out.println("스레드 1이 락을 획득했습니다.");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 1이 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 1이 락 획득에 실패했습니다. 잠시 대기 후 재시도합니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            boolean acquired = false;

            while (!acquired) {
                // 락 획득 시도
                acquired = lock.tryLock();

                if (acquired) {
                    try {
                        System.out.println("스레드 2가 락을 획득했습니다.");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 2가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 2가 락 획득에 실패했습니다. 잠시 대기 후 재시도합니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

    }
}

/*
    스레드 2가 락을 획득했습니다.
    스레드 1이 락 획득에 실패했습니다. 잠시 대기 후 재시도합니다.
    스레드 1이 락 획득에 실패했습니다. 잠시 대기 후 재시도합니다.
    스레드 2가 락을 해제했습니다.
    스레드 1이 락을 획득했습니다.
    스레드 1이 락을 해제했습니다.
 */