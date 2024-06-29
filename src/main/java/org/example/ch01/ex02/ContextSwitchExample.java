package org.example.ch01.ex02;

public class ContextSwitchExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 3: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        /* 출력
                Thread 1: 0
                Thread 3: 0
                Thread 2: 0
                Thread 3: 1
                Thread 1: 1
                Thread 2: 1
                Thread 3: 2
                Thread 2: 2
                Thread 1: 2
                Thread 3: 3
                Thread 2: 3
                Thread 1: 3
                Thread 3: 4
                Thread 2: 4
                Thread 1: 4
         */

        // 스레드 1, 2, 3이 동일하게 0~4를 출력하는데 스레드 순서가 무작위이다.
        // 어떠한 규칙이나 기준이 없이 무작위인 것은 아니고 컨텍스트 스위치가 일어나기 때문에 가능한 것.
        // 스케줄링 알고리즘에 의해 스레드마다 CPU를 할당받은 순서대로 출력된 것이다.
    }
}
