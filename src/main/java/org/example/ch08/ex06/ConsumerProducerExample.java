package org.example.ch08.ex06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExample {
    private static final int CAPACITY = 5;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            lock.lock();

            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("큐가 가득 차서 대기..");
                    notFull.await();
                }

                queue.offer(value);
                System.out.println("생산: " + value + ", 큐 크기: " + queue.size());
                value++;

                // 큐에 데이터가 추가되었으므로 소비자에게 알림
                notEmpty.signal();
            } finally {
                lock.unlock();
            }

            Thread.sleep(500);
        }
    }

    private void consumer() throws InterruptedException {
        while (true) {
            lock.lock();

            try {
                while (queue.isEmpty()) {
                    System.out.println("큐가 비어 있어서 대기..");
                    notEmpty.await();
                }

                int value = queue.poll();
                System.out.println("소비: " + value + ", 큐 크기: " + queue.size());

                // 큐의 데이터를 소비했으니까 생산자에게 알림
                notFull.signal();
            } finally {
                lock.unlock();
            }

            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        ConsumerProducerExample example = new ConsumerProducerExample();

        // 생산자 스레드
        Thread producer = new Thread(() -> {
            try {
                example.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 소비자 스레드
        Thread consumer = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
