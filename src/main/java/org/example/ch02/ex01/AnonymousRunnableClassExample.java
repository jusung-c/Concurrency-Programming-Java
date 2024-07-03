package org.example.ch02.ex01;

public class AnonymousRunnableClassExample {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            }
        });

        thread.start();
    }
}

// 출력: Thread-0 : 스레드 실행 중..