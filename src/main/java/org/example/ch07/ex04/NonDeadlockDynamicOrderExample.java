package org.example.ch07.ex04;

/*
    lockA와 lockB의 해시 코드 크기를 비교해 lock의 순서를 조정한다.
    해시 코드가 크면 먼저 락을 얻도록 해 락의 순서를 일정하도록 해 데드락을 방지하는 전략

    출력:
    Thread-0:  java.lang.Object@31e10da 획득
    Thread-0:  java.lang.Object@dd9eec9 획득
    Thread-1:  java.lang.Object@dd9eec9 획득
    Thread-1:  java.lang.Object@31e10da 획득
 */
public class NonDeadlockDynamicOrderExample {
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

        Object firstLock = lockA;
        Object secondLock = lockB;

        if (System.identityHashCode(lockA) > System.identityHashCode(lockB)) {
            firstLock = lockB;
            secondLock = lockA;
        }

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + ":  " + lockA + " 획득");

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + ":  " + lockB + " 획득");
            }
        }
    }
}
