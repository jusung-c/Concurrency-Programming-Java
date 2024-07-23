package org.example.ch07.ex03;

/*
    volatile이 없으면 값을 가져올 때 메인 메모리에서 가져오는 것이 아닌
    CPU 캐시에서 가져오기 때문에 false로 변경한 값이 반영되지 않아서 무한루프에 빠지는 것이다.
 */
public class VolatileExample {
    volatile boolean running = true;
//    boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;

            while (running) {
                count++;
            }

            System.out.println("Thread 1 종료. Count: " + count);

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            System.out.println("Thread 2 종료 중..");
            running = false;
        }).start();
    }

    public static void main(String[] args) {
        new VolatileExample().volatileTest();
    }
}
