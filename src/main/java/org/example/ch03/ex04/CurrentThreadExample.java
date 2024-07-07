package org.example.ch03.ex04;

public class CurrentThreadExample {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("현재 스레드(main): " + Thread.currentThread());
        System.out.println("현재 스레드 이름(main): " + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("현재 스레드(thread): " + Thread.currentThread());
                System.out.println("현재 스레드(thread): " + Thread.currentThread().getName());
                System.out.println("현재 스레드(thread): " + getName());
            }
        };
        thread.start();

        Thread thread1 = new Thread(new ThreadName());
        thread1.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {
            System.out.println("현재 스레드(Runnable 사용): " + Thread.currentThread());
            System.out.println("현재 스레드 이름(Runnable 사용): " + Thread.currentThread().getName());
        }
    }

}

/*
    현재 스레드(main): Thread[main,5,main]
    현재 스레드 이름(main): main
    현재 스레드(thread): Thread[Thread-0,5,main]
    현재 스레드(thread): Thread-0
    현재 스레드(thread): Thread-0
    현재 스레드(Runnable 사용): Thread[Thread-1,5,main]
    현재 스레드 이름(Runnable 사용): Thread-1
 */