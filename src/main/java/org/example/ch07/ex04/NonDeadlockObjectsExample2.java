package org.example.ch07.ex04;

/*
    각각의 락을 작업 후 해제해서 서로가 원하는 락을 얻어 데드락을 방지하는 전략

    출력:
    Thread-0: methodA의 ResourceA 부분 실행
    Thread-0: methodB2 실행
    Thread-1: methodB의 ResourceA 부분 실행
    Thread-1: methodA2 실행
 */
public class NonDeadlockObjectsExample2 {
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


    static class ResourceA {

        public void methodA(ResourceB resourceB) {
            synchronized (ResourceA.class) { // 첫 번째로 ResourceA 락 획득
                System.out.println(Thread.currentThread().getName() + ": methodA의 ResourceA 부분 실행");
            }
            resourceB.methodB2();
        }

        public synchronized void methodA2() {
            System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
        }
    }

    static class ResourceB {

        public void methodB(ResourceA resourceA) {
            synchronized (ResourceA.class) { // 첫 번째로 ResourceA 락 획득
                System.out.println(Thread.currentThread().getName() + ": methodB의 ResourceA 부분 실행");
            }
            resourceA.methodA2();
        }

        public synchronized void methodB2() {
            System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
        }
    }
}

