package org.example.ch02.ex03;

public class BlockedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // 무한 루프로 락을 계속 들고 있음
                    while (true) {

                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    // thread1이 락을 안 풀어줘서 출력 안댐
                    System.out.println("lock을 획득하려고 시도");
                }
            }
        });

        thread1.start();
        // thread1.start(); // 2번 실행 안댐
        Thread.sleep(100);

        thread2.start();
        Thread.sleep(100);

        System.out.println("스레드1 상태: " + thread1.getState());
        System.out.println("스레드2 상태: " + thread2.getState());
    }
}

/*
        스레드1 상태: RUNNABLE
        스레드2 상태: BLOCKED
 */
