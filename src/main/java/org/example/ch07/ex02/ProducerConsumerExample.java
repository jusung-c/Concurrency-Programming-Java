package org.example.ch07.ex02;

import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5; // 큐의 최대 용량
    private final Object lock = new Object();

    // 아이템을 큐에 추가 (생산)
    public void produce(int item) throws InterruptedException {
        synchronized (lock) {

            // 큐가 가득 찼는지 조건 검사
            while (queue.size() == CAPACITY) {
                System.out.println("큐가 가득 찼습니다.. 생산 중지..");
                lock.wait();
            }

            queue.add(item);
            System.out.println("생산: " + item);

            // 대기 중인 소비자에게 알림
            lock.notifyAll();
        }
    }

    // 아이템을 큐에서 제거 (소비)
    public void consume() throws InterruptedException {
        synchronized (lock) {

            // 큐가 비어 있는지 조건 검사
            while (queue.isEmpty()) {
                System.out.println("큐가 비어있습니다.. 소비 중지..");
                lock.wait();
            }

            int item = queue.poll();
            System.out.println("소비: " + item);

            // 대기 중인 생산자에게 알림
            lock.notifyAll();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        // 생산자 스레드
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sharedQueue.produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "생산자 스레드");

        // 소비자 스레드
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sharedQueue.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "소비자 스레드");

        producer.start();
        consumer.start();

    }
}

/*
    생산: 0
    생산: 1
    생산: 2
    생산: 3
    생산: 4
    큐가 가득 찼습니다.. 생산 중지..
    소비: 0
    소비: 1
    소비: 2
    소비: 3
    소비: 4
    큐가 비어있습니다.. 소비 중지..
    생산: 5
    생산: 6
    생산: 7
    생산: 8
    생산: 9
    소비: 5
    소비: 6
    소비: 7
    소비: 8
    소비: 9
 */