package org.example.ch04.ex02;

/*
    원자성을 보장하고 스레드 간 공유 데이터를 안전하게 사용하기 위해 `AtomicBoolean` 객체를 사용한다.
    - `AtomicBoolean`은 원자적 연산을 제공하여 다중 스레드 환경에서 동기화 없이도 안전하게 boolean 값을 읽고 쓸 수 있다.
    - 각각의 CPU 캐시 메모리가 아닌 메인 메모리에서 공유 데이터를 동시에 같이 읽어올 수 있도록 보장한다.
 */

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {
    //    boolean running = true;
    private AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) {
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("스레드 1 종료, count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드 2 종료");
            running.set(false);

        }).start();
    }
}

/*
    스레드 2 종료
    스레드 1 종료, count: 44215206
 */
