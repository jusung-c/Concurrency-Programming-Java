package org.example.ch02.ex01;

public class ExtendThreadExample {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
    }
}

// 출력: Thread-0 : 스레드 실행 중..