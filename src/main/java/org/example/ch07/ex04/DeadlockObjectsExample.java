package org.example.ch07.ex04;

/*
    두 객체가 서로 락을 무한정 기다리면서 데드락 발생
 */
public class DeadlockObjectsExample {
    public static void main(String[] args) {

        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread thread1 = new Thread(() -> {
            resourceA.methodA(resourceB);
        });

        Thread thread2 = new Thread(() -> {
            resourceB.methodB(resourceA);
        });

        thread1.start();
        thread2.start();
    }
}

class ResourceA {

    public synchronized void methodA(ResourceB resourceB) {
        System.out.println(Thread.currentThread().getName() + ": methodA 실행");
        try {
            // 데드락 발생시키기 위해 대기
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        resourceB.methodB2();
    }

    public synchronized void methodA2() {
        System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
    }
}

class ResourceB {

    public synchronized void methodB(ResourceA resourceA) {
        System.out.println(Thread.currentThread().getName() + ": methodB 실행");
        try {
            // 데드락 발생시키기 위해 대기
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        resourceA.methodA2();
    }

    public synchronized void methodB2() {
        System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
    }
}
