package org.example.ch07.ex04;

/*
    이전의 객체로 인한 데드락 발생 코드와 다르게 메서드에 synchronized가 아닌 블록 단위로 걸어주고
    객체 락을 얻는 순서를 일치시켜서 데드락을 방지한다.
    먼저 ResourceA 락을 선점한 스레드가 ResourceB 락도 획득하고 작업을 마친 뒤에 그 다음 스레드가 시작된다.

    출력:
    Thread-0: methodA의 ResourceA 부분 실행
    Thread-0: methodA의 ResourceB 부분 실행
    Thread-0: methodB2 실행
    Thread-1: methodB의 ResourceA 부분 실행
    Thread-1: methodB의 ResourceB 부분 실행
    Thread-1: methodA2 실행
 */
public class NonDeadlockObjectsExample {
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

                synchronized (ResourceB.class) { // 두 번째로 ResourceB 락 획득
                    System.out.println(Thread.currentThread().getName() + ": methodA의 ResourceB 부분 실행");
                    resourceB.methodB2();
                }
            }
        }

        public void methodA2() {
            synchronized (ResourceA.class) {
                System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
            }
        }
    }

    static class ResourceB {

        public void methodB(ResourceA resourceA) {
            synchronized (ResourceA.class) { // 첫 번째로 ResourceA 락 획득
                System.out.println(Thread.currentThread().getName() + ": methodB의 ResourceA 부분 실행");

                synchronized (ResourceB.class) { // 두 번째로 ResourceB 락 획득
                    System.out.println(Thread.currentThread().getName() + ": methodB의 ResourceB 부분 실행");
                    resourceA.methodA2();
                }
            }
        }

        public void methodB2() {
            synchronized (ResourceB.class) {
                System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
            }
        }
    }
}

