package org.example.ch04.ex05;

public class ThreadLocalExample {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello World!");

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.set("스레드 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        }, "Thread-1").start();

        /*
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.set("스레드 2의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove();
        }, "Thread-2").start();
        */
    }
}

/*
    Thread-1 : Hello World!
    Thread-1 : 스레드 1의 값
    Thread-1 : Hello World!
    Thread-2 : Hello World!
    Thread-2 : 스레드 2의 값
 */