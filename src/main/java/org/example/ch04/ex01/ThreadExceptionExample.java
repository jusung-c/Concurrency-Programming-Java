package org.example.ch04.ex01;

/*
    예외가 발생은 하지만 catch 문으로 잡히지 않는다.
 */

public class ThreadExceptionExample {
    public static void main(String[] args) {

        try {
            new Thread(() -> {
                throw new RuntimeException("스레드 예외 발생");
            }).start();

        } catch (Exception e) {
            notify(e);
        }
    }

    private static void notify(Exception e) {
        System.out.println("관리자에게 알림: " + e);
    }
}
