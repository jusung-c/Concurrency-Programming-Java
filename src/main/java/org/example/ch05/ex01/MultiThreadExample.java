package org.example.ch05.ex01;

/*
    멀티 스레드는 한 스레드에서 에러가 발생해도 다른 스레드에 영향을 미치지 않는다.
    또한 멀티 코어로 병렬적으로 처리하기 때문에 단일 스레드보다 작업 속도가 훨씬 빠르다.

    하지만 멀티 스레드의 치명적인 단점
    - 각 스레드가 공유 필드인 sum을 동시에 사용하기 때문에 동시성 문제가 발생해 데이터의 정합성이 깨질 수 있다.
    - 이번 예제에서는 synchronized를 걸어서 해결했다. -> 없이 돌리면 계속 합계의 값이 조금씩 틀리게 나온다.
 */

public class MultiThreadExample {
    private static int sum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                synchronized (lock) {
                    sum += i;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }

                try {
                    Thread.sleep(1);
//                    throw new RuntimeException("error");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("합계: " + sum);
        System.out.println("멀티 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
