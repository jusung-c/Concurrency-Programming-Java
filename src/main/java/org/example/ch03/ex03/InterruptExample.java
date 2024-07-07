package org.example.ch03.ex03;

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("스레드 1 작업 시작..");
            System.out.println("스레드 1 인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드 2가 스레드 1을 인터럽트 합니다..");
            thread1.interrupt();
            System.out.println("스레드 2 인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        thread2.start();
        Thread.sleep(1000);
        thread1.start();

        thread1.join();
        thread2.join();

        System.out.println("모든 스레드 작업 완료");
    }
}

/*
    스레드 2가 스레드 1을 인터럽트 합니다..
    스레드 2 인터럽트 상태: false
    스레드 1 작업 시작..
    스레드 1 인터럽트 상태: true
    모든 스레드 작업 완료
 */