package org.example.ch07.ex04;

/*
    동적으로 락이 결정될 때 서로가 필요한 락을 보유하면서 서로가 무한 대기하는 데드락에 빠지는 경우
 */
public class DeadlockDynamicOrderExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            methodWithLocks(lock1, lock2);
        }).start();

        new Thread(() -> {
            methodWithLocks(lock2, lock1);
        }).start();
    }

    private static void methodWithLocks(Object lockA, Object lockB) {

        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + ":  " + lockA + " 획득");

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + ":  " + lockB + " 획득");
            }
        }
    }
}
