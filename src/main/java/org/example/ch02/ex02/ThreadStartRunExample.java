package org.example.ch02.ex02;

public class ThreadStartRunExample {
    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
//        thread.run();
//        myRunnable.run();

        System.out.println(Thread.currentThread().getName() + ": 메인 스레드 종료");
        /* 출력 (스레드 실행 순서는 매번 바뀜 - 비동기적)
            main: 메인 스레드 종료
            Thread-2 : 스레드 실행 중..
            Thread-1 : 스레드 실행 중..
            Thread-0 : 스레드 실행 중..
         */
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
        }
    }

}
