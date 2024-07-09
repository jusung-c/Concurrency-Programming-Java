package org.example.ch04.ex05.logger;

/*
    각 스레드마다 다른 로그가 찍힌다. -> 각 스레드마다 다른 로컬 스레드 공간을 가진다
 */

public class ThreadLocalLoggerExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new LogWorker());
        Thread thread2 = new Thread(new LogWorker());
        Thread thread3 = new Thread(new LogWorker());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
    [Thread-2]: ServiceB 로직 수행 -> ServiceA 로직 수행 -> ServiceC 로직 수행
    [Thread-1]: ServiceA 로직 수행 -> ServiceB 로직 수행 -> ServiceC 로직 수행
    [Thread-0]: ServiceC 로직 수행 -> ServiceA 로직 수행 -> ServiceB 로직 수행
 */