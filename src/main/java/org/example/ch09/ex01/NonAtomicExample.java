package org.example.ch09.ex01;

public class NonAtomicExample {
    private static int value = 0;
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                // 여러번 반복해 확률적으로 경쟁 조건 발생시키기
                for (int j = 0; j < 100000; j++) {
                    int expectedValue = value;
                    int newValue = expectedValue + 1;

                    value = newValue;
                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + " , " + newValue);
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final value: " + value);
    }
}
