package org.example.ch07.ex01.method;

public class StaticMethodSynchronizedExamples {
    private static int count = 0;

    public static synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
    }

    public static synchronized void decrement(){
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
    }

    public static int getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                StaticMethodSynchronizedExamples.increment();
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                StaticMethodSynchronizedExamples.decrement();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("최종값: " + StaticMethodSynchronizedExamples.getCount());
    }
}
