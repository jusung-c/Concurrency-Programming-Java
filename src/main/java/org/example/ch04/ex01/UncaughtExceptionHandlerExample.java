package org.example.ch04.ex01;

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("스레드 1 시작!");

            // 예기치 않은 예외 발생
            throw new RuntimeException("예기치 않은 예외!");
        });

        thread1.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외 발생: " + e);
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드 2 시작!");

            // 예기치 않은 예외 발생
            throw new RuntimeException("예기치 않은 예외!");
        });

        thread2.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외가 발생했어요: " + e);
        });

        thread2.start();
    }
}

/*
    스레드 1 시작!
    스레드 2 시작!
    Thread-0 에서 예외 발생: java.lang.RuntimeException: 예기치 않은 예외!
    Thread-1 에서 예외가 발생했어요: java.lang.RuntimeException: 예기치 않은 예외!
 */