package org.example.ch03.ex03;

public class IsInterruptedExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("스레드가 작동 중입니다..");
            }

            System.out.println("스레드가 인터럽트 되었습니다.");
            System.out.println("스레드 인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

/*
    스레드가 작동 중입니다..
    스레드가 작동 중입니다..
    스레드가 인터럽트 되었습니다.
    스레드 인터럽트 상태: true
 */