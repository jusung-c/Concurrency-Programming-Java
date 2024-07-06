package org.example.ch03.ex02;

/*
    부모: 메인 스레드
    자식: 생성한 스레드

    메인 스레드가 자식 스레드의 작업이 완료될 때까지 자신의 작업을 하지 않고 대기하다가
    자식 스레드의 작업이 끝나면 다시 작동한다.

 */

public class BasicJoinExample {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("스레드 3초 작동");
                Thread.sleep(3000);
                System.out.println("스레드 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다린다.");

        try {

            // 호출한 메인 스레드가 대기
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("메인 스레드가 계속 진행합니다.");

    }
}

/*
    메인 스레드가 다른 스레드의 완료를 기다린다.
    스레드 3초 작동
    스레드 작동 완료
    메인 스레드가 계속 진행합니다.
 */