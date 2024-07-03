package org.example.ch02.ex03;

public class TimedWaitingStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        Thread.sleep(100);

        // 1초 기다리는 동안에 출력문이 실행되므로 TIMED_WAITING
        System.out.println("스레드 상태: " + thread.getState());
    }
}

// 출력: 스레드 상태: TIMED_WAITING
