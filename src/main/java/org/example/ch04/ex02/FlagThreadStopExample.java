package org.example.ch04.ex02;

/*
    각 스레드마다 CPU 캐시 메모리를 가지고 있기 때문에 volatile이 아니라면 메모리에 반영되지 않아서
    원하는 대로 동작하지 않는다.

    그런데 무한 루프 안에서 아주 잠깐 sleep을 걸어주면 volatile 키워드 없이도 잘 작동된다.
    왜일까?

    sleep에 걸리는 순간 컨텍스트 스위칭이 일어나는데 이 때 CPU 캐시의 정보를 비워야 한다.
    따라서 첫번째 스레드의 running(true) 상태가 지워지는 것이다. 대기 상태에서 깨어나 다시 러닝 상태가 되면
    이미 캐시 정보는 사라진 상태이고 다시 메모리로부터 가져와야 하니까 false값을 가져오게 되는 것이다.
 */

public class FlagThreadStopExample {
//    private volatile static boolean running = true;
    private static boolean running = true;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;

            while (running) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }

            System.out.println("스레드 1 종료, count: " + count);

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드 2 종료");
            running = false;

        }).start();
    }
}

/*
    스레드 2 종료
    스레드 1 종료, count: 400
 */
