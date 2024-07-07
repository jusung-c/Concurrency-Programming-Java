package org.example.ch04.ex02;

public class InterruptedExceptionThreadStopExample5 {
    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("작업 스레드 실행 중..");
                    System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
                    if (Thread.interrupted())
                        throw new InterruptedException("thread is interrupted");
                }

            } catch (InterruptedException e) {
                // 자바에서 제공하는 sleep(), join() 등에서 빠져나온 것이 아니므로 예외처리를 한다고 해도 인터럽트가 초기화되지 않는다.
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }

            System.out.println("작업 스레드 종료");
            System.out.println("인터럽트 상태 3: " + Thread.currentThread().isInterrupted());
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
    }
}

/*
    작업 스레드 실행 중..
    인터럽트 상태 1: false
    작업 스레드 실행 중..
    인터럽트 상태 1: false
    중단 스레드가 작업 스레드를 종료
    인터럽트 상태 2: false
    작업 스레드 종료
    인터럽트 상태 3: true
 */