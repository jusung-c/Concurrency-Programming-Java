package org.example.ch03.ex01;

public class InterruptSleepExample {
    public static void main(String[] args) throws InterruptedException {

        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("20초 동안 수면에 들어갑니다..");
                Thread.sleep(20000);
                System.out.println("인터럽트 없이 잠에서 깨어났습니다.");

            } catch (InterruptedException e) {
                System.out.println("잠들어 있는 동안 인터럽트가 발생했습니다.");
            }
        });

        sleepingThread.start();

        Thread.sleep(1000);

        // 인터럽트 발생
        sleepingThread.interrupt();

    }
}

/*
    20초 동안 수면에 들어갑니다..
    잠들어 있는 동안 인터럽트가 발생했습니다.
 */