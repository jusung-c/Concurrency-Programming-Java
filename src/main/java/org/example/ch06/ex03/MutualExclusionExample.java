package org.example.ch06.ex03;

public class MutualExclusionExample {
    private int counter = 0;

    public synchronized void increment() {
        counter++;

        System.out.println("스레드: " + Thread.currentThread().getName()
                + ", 카운터 값: " + counter);
    }

    public static void main(String[] args) {
        MutualExclusionExample example = new MutualExclusionExample();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
            }
        }).start();
    }
}

/*
스레드: Thread-0, 카운터 값: 1
스레드: Thread-0, 카운터 값: 2
스레드: Thread-0, 카운터 값: 3
스레드: Thread-0, 카운터 값: 4
스레드: Thread-0, 카운터 값: 5
스레드: Thread-1, 카운터 값: 6
스레드: Thread-1, 카운터 값: 7
스레드: Thread-1, 카운터 값: 8
스레드: Thread-1, 카운터 값: 9
스레드: Thread-1, 카운터 값: 10
 */