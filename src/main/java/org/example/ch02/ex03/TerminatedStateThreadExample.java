package org.example.ch02.ex03;

public class TerminatedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        thread.start();
        thread.join();  // 스레드가 다 실행될 때까지 기다림

        // 실행을 하지 않고 상태 보기
        System.out.println("스레드 상태: " + thread.getState());
    }
}

// 출력: 스레드 상태: TERMINATED
