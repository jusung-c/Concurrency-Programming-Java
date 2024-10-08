package org.example.ch11.ex01;

public class AsynchronousCall {
    public static void main(String[] args) {
        // 비동기 실행
        asyncCall();

        System.out.println("결과를 기다리지 않습니다.");
        System.out.println("메인 스레드 종료");
    }

    private static void asyncCall() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("비동기 실행 완료");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
