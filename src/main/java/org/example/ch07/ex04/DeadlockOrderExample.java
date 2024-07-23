package org.example.ch07.ex04;
/*
    두 스레드가 서로의 락을 기다리면서 무한 대기에 빠진다. -> 데드락 발생
 */

public class DeadlockOrderExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            process1();
        }).start();

        new Thread(() -> {
            process2();
        }).start();
    }

    private static void process1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " 이 lock1 을 획득하였습니다.");

            try {
                // 경쟁시키기 위해 잠시 대기
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " 이 lock2 을 획득하였습니다.");
            }
        }
    }

    private static void process2() {
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " 이 lock2 을 획득하였습니다.");

            try {
                // 경쟁시키기 위해 잠시 대기
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " 이 lock1 을 획득하였습니다.");
            }
        }
    }

}
