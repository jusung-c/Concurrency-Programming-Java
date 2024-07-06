package org.example.ch03.ex02;

public class MultiThreadJoinExample {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("스레드1 3초 작동");
                Thread.sleep(3000);
                System.out.println("스레드1 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("스레드2 2초 작동");
                Thread.sleep(2000);
                System.out.println("스레드2 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다린다.");

        try {

            // 호출한 메인 스레드가 대기
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("메인 스레드가 계속 진행합니다.");

    }
}

/*
    메인 스레드가 다른 스레드의 완료를 기다린다.
    스레드1 3초 작동
    스레드2 2초 작동
    스레드2 작동 완료
    스레드1 작동 완료
    메인 스레드가 계속 진행합니다.
 */