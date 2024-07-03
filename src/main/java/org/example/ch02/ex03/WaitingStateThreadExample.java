package org.example.ch02.ex03;

public class WaitingStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread.start();
        Thread.sleep(100);

        System.out.println("스레드 상태: " + thread.getState());
    }
}

// 출력: 스레드 상태: WAITING
