package org.example.ch05.ex04;

/*
    지역 변수, 매개변수는 독립적으로 복사본을 가지기 때문에 스레드 간 안전하다.
    따라서 결과가 독립적으로 다르게 나올 것.

    만약 멤버 변수로 localSum을 사용하면 동시성 문제가 발생해
    스레드 간 안전하지 않아 결과가 실행할 때마다 다르게 나온다.
 */

public class ThreadSafeLocalVariableExample {

//    int localSum = 0;
    public void printNumbers(int plus) {
        // 지역 변수, 매개 변수로 정의.
        int localSum = 0;

        for (int i = 1; i <= 5; i++) {
            localSum += i;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        localSum += plus;

        System.out.println(Thread.currentThread().getName() + " - 현재 합계: " + localSum);
    }

    public static void main(String[] args) {
        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        Thread thread1 = new Thread(() -> {
            example.printNumbers(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumbers(40);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}

/*
    Thread-1 - 현재 합계: 65
    Thread-2 - 현재 합계: 55
 */