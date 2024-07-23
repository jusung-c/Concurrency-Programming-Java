package org.example.ch07.ex01.method;

public class InstanceMethodSynchronizedExamples {
    private int count = 0;

    public synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
    }

    public synchronized void decrement(){
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        var counter = new InstanceMethodSynchronizedExamples();

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.decrement();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("최종값: " + counter.getCount());
    }
}
