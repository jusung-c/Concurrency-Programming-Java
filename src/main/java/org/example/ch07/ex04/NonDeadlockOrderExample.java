package org.example.ch07.ex04;

/*
    락 획득 순서를 일정하게 해 데드락을 방지한다. 순차적으로 락 획득.

    출력
    Thread-0 이 lock1 을 획득하였습니다.
    Thread-0 이 lock2 을 획득하였습니다.
    Thread-1 이 lock1 을 획득하였습니다.
    Thread-1 이 lock2 을 획득하였습니다.
 */
public class NonDeadlockOrderExample {
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

}
