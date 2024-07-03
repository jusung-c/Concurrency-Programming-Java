package org.example.ch02.ex01;

public class LambdaThreadExample {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중.."));
        thread.start();

    }
}

// 출력: Thread-0 : 스레드 실행 중..
