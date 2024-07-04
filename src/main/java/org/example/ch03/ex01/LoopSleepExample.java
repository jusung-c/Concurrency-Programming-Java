package org.example.ch03.ex01;

public class LoopSleepExample {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            try {
                System.out.println("1초마다 메세지가 출력됩니다. 반복: " + (i+1));
                Thread.sleep(1000);
                System.out.println("Hello World!");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
