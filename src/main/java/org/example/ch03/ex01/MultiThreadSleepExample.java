package org.example.ch03.ex01;

public class MultiThreadSleepExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("1초 후에 메세지가 출력됩니다.");
                Thread.sleep(1000);
                System.out.println("스레드 1번이 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("2초 후에 메세지가 출력됩니다.");
                Thread.sleep(2000);
                System.out.println("스레드 2번이 깨어났습니다.");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("여기는 메인입니다.");

    }
}

/* 출력
        1초 후에 메세지가 출력됩니다.
        2초 후에 메세지가 출력됩니다.
        여기는 메인입니다.
        스레드 1번이 깨어났습니다.
        스레드 2번이 깨어났습니다.
 */
