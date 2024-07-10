package org.example.ch05.ex01;

/*
    메인 스레드(단일 스레드)에서 에러 발생 시 프로그램 종료된다.
 */

public class SingleThreadExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = 0;

        for (int i = 1; i <= 1000; i++) {
            sum += i;

            try {
                Thread.sleep(1);
//                throw new RuntimeException("error");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("합계: " + sum);
        System.out.println("싱글 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
