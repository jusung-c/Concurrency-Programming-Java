package org.example.ch03.ex05;

public class ThreadPriorityAPIExample {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("기본 우선 순위: " + thread.getPriority());
        thread.start();

        Thread minThread = new Thread(() -> {
            System.out.println("최소 우선 순위: " + Thread.currentThread().getPriority());
        });

        minThread.setPriority(Thread.MIN_PRIORITY);
        minThread.start();

        Thread normThread = new Thread(() -> {
            System.out.println("기본 우선 순위: " + Thread.currentThread().getPriority());
        });

        normThread.setPriority(Thread.NORM_PRIORITY);
        normThread.start();

        Thread maxThread = new Thread(() -> {
            System.out.println("최대 우선 순위: " + Thread.currentThread().getPriority());
        });

        maxThread.setPriority(Thread.MAX_PRIORITY);
        maxThread.start();
    }
}
/*
    기본 우선 순위: 5
    최소 우선 순위: 1
    기본 우선 순위: 5
    최대 우선 순위: 10
 */
