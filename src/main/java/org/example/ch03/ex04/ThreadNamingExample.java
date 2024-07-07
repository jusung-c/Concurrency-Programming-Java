package org.example.ch03.ex04;

public class ThreadNamingExample {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(() -> {
            System.out.println("현재 스레드 이름: " + Thread.currentThread().getName());
        }, "myThread");
        myThread.start();

        Thread yourThread = new Thread(() -> {
            System.out.println("현재 스레드 이름: " + Thread.currentThread().getName());
        }, "myThread");
        yourThread.setName("yourThread");
        yourThread.start();

        for (int i = 0; i < 5; i++) {
            Thread defaultThread = new Thread(() -> {
                System.out.println("현재 스레드 이름: " + Thread.currentThread().getName());
            });

            defaultThread.start();
        }

        Thread.sleep(2000);
    }
}

/*
    현재 스레드 이름: yourThread
    현재 스레드 이름: Thread-2
    현재 스레드 이름: Thread-1
    현재 스레드 이름: myThread
    현재 스레드 이름: Thread-3
    현재 스레드 이름: Thread-0
    현재 스레드 이름: Thread-4
 */