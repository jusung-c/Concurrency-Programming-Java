package org.example.ch04.ex02;

public class InterruptedThreadStopExample {
    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println("작업 스레드 실행 중..");
            }

            System.out.println("작업 스레드 종료");
            System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());

            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 종료");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }
}

/*
    ..
    작업 스레드 실행 중..
    작업 스레드 실행 중..
    중단 스레드가 작업 스레드를 종료
    작업 스레드 종료
    인터럽트 상태 1: false
    인터럽트 상태 2: true
 */