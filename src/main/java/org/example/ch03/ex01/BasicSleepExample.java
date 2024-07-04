package org.example.ch03.ex01;

public class BasicSleepExample {
    public static void main(String[] args) {
        try {
            System.out.println("2초 후에 메세지가 출력됩니다.");
            Thread.sleep(2000);
            System.out.println("Hello World!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
