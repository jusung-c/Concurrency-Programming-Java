package org.example.ch04.ex03;

public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) throws InterruptedException {

        Thread userThread = new Thread(() -> {
            // 자식 스레드 생성
            new Thread(() -> {
                System.out.println("사용자 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            // 자식 스레드 생성
            new Thread(() -> {
                System.out.println("데몬 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("데몬 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        // 데몬 스레드 설정
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join(); // 데몬 스레드는 대기할 필요 없음

        System.out.println("메인 스레드 종료");
    }
}

/*
    사용자 스레드의 데몬 상태: false
    사용자 스레드의 자식 스레드의 데몬 상태: false
    메인 스레드 종료
    데몬 스레드의 자식 스레드의 데몬 상태: true
    데몬 스레드의 데몬 상태: true
 */
