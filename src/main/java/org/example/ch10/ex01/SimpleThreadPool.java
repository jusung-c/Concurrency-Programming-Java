package org.example.ch10.ex01;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {

    private final int numThreads;                   // 스레드 풀 내 스레드 수
    private final Queue<Runnable> taskQueue;        // 작업 큐
    private final Thread[] threads;                 // 스레드 배열
    private volatile boolean isShutdown;            // 종료 여부 플래그

    public SimpleThreadPool(int numThreads) {
        // 초기화
        this.numThreads = numThreads;
        this.taskQueue = new LinkedList<>();
        this.threads = new Thread[numThreads];
        this.isShutdown = false;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new WorkerThread();    // 작업자 스레드 생성
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task);      // 작업 큐에 작업 추가
                taskQueue.notifyAll();      // 대기중인 스레드에게 작업이 추가되었음을 알린다.
            }
        }
    }

    public void shutdown() {
        isShutdown = true;      // 스레드 풀 종료 플래그 설정

        synchronized (taskQueue) {
            taskQueue.notifyAll();  // 대기중인 모든 스레드를 깨워 종료하도록
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // 각 스레드가 종료될 때까지 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class WorkerThread extends Thread {
        public void run() {
            while (!isShutdown) {
                Runnable task;

                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.poll();    // 작업 큐에서 가져온다.
                    } else {
                        continue;       // 작업이 없으면 다시 대기
                    }
                }

                try {
                    task.run();
                } catch (Throwable throwable) {
                    // 예외 로직 추가
                    throwable.printStackTrace();
                }
            }
        }
    }

}

