package org.example.ch02.ex02;

public class MultiThreadAppTerminatedExample {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));

            thread.start();
        }

        System.out.println("메인 스레드 종료");

    }

    static class MyRunnable implements Runnable {
        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {

            int localValue = threadId + 100;
            secondMethod(localValue);

        }

        private void secondMethod(int localValue) {
            String objectReference = threadId + "Hello World";
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID: " + threadId + ", Value: " + localValue);
        }
    }
}

// 멀티 쓰레드 환경에서 쓰레드가 단 하나라도 종료되지 않으면 애플리케이션이 종료되지 않는다.

/*
        메인 스레드 종료
        Thread-0 : 스레드 실행 중..
        Thread-1 : 스레드 실행 중..
        Thread-2 : 스레드 실행 중..
        Thread-0 : 스레드 ID: 0, Value: 100
        Thread-1 : 스레드 ID: 1, Value: 101
        Thread-2 : 스레드 ID: 2, Value: 102
 */


