package org.example.ch04.ex03;

public class DaemonThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("사용자 스레드 실행 중..");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("데몬 스레드 실행 중..");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // 데몬 스레드 설정
        daemonThread.setDaemon(true);
//        daemonThread.setDaemon(false);

        userThread.start();
        daemonThread.start();

        // 스레드 시작 후에 설정하면 예외 발생 -> IllegalThreadStateException
//        daemonThread.setDaemon(true);

        userThread.join(); // 데몬 스레드는 대기할 필요 없음

        System.out.println("메인 스레드 종료");
    }
}

/*
    데몬 스레드 실행 중..
    데몬 스레드 실행 중..
    데몬 스레드 실행 중..
    데몬 스레드 실행 중..
    데몬 스레드 실행 중..
    사용자 스레드 실행 중..
    메인 스레드 종료
 */
