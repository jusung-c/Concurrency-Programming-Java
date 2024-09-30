package org.example.ch11.ex01;

public class SyncBlocking {
    public static void main(String[] args) {

        // 동기 & 블로킹
        blocking();
        System.out.println("메인 스레드 종료");
    }

    private static void blocking() {
        try {
            Thread.sleep(3000);
            System.out.println("작업 종료");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
