package org.example.ch11.ex01;

public class SynchronousCall {
    public static void main(String[] args) {
        // 동기 실행
        int result = syncCall();

        System.out.println("result = " + result);
        System.out.println("메인 스레드 종료");
    }

    private static int syncCall() {

        try {
            Thread.sleep(1000);
            System.out.println("동기 실행 완료-1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 10;
    }
}
