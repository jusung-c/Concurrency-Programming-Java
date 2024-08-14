package org.example.ch08.ex06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() throws InterruptedException {
        lock.lock();

        try {
            while (!flag) {
                System.out.println("조건 불만족. 대기..");
                condition.await();
            }
            System.out.println("임계영역 수행..");
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();

        try {
            flag = true;
            System.out.println("조건 만족. 깨우기..");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample conditionExample = new ConditionExample();

        Thread thread1 = new Thread(() -> {
            try {
                conditionExample.awaiting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(conditionExample::signaling);

        thread1.start();
        Thread.sleep(2000);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
