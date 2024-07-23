package org.example.ch07.ex01.method;

// 스레드에 모니터 락이 여러개인 경우 서로 다른 모니터에 대해서는 동시 접근이 불가능하다.
public class InstanceMethodSynchronizedExamples2 {
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
        var counter1 = new InstanceMethodSynchronizedExamples2();
        var counter2 = new InstanceMethodSynchronizedExamples2();

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter1.increment();
                counter2.decrement();
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter1.decrement();
                counter2.increment();
            }
        });

        th1.start();
        th2.start();
        
        th1.join();
        th2.join();

        System.out.println("counter1 최종값: " + counter1.getCount());
        System.out.println("counter2 최종값: " + counter2.getCount());
    }
}
