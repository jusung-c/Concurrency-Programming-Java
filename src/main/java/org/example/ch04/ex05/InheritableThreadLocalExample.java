package org.example.ch04.ex05;

public class InheritableThreadLocalExample {

//    public static ThreadLocal<String> inheritableThreadLocal = new ThreadLocal<>();
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        inheritableThreadLocal.set("부모 스레드의 값");

        Thread childThread = new Thread(() -> {
            System.out.println("자식 스레드에서 부모로부터 상속받은 값: " + inheritableThreadLocal.get());

            // 자식에서 부모의 값 변경
            inheritableThreadLocal.set("자식 스레드의 새로운 값");
            System.out.println("자식 스레드에서 설정한 후의 값: " + inheritableThreadLocal.get());
        });

        childThread.start();

        try {
            childThread.join();
        } catch (InterruptedException e) {

        }

        // 자식이 변경해도 부모에게 영향 X
        System.out.println("부모 스레드의 값: " + inheritableThreadLocal.get());

    }
}

/*  ThreadLocal로 했을 때
    자식 스레드에서 부모로부터 상속받은 값: null
    자식 스레드에서 설정한 후의 값: 자식 스레드의 새로운 값
    부모 스레드의 값: 부모 스레드의 값
 */

/*
    자식 스레드에서 부모로부터 상속받은 값: 부모 스레드의 값
    자식 스레드에서 설정한 후의 값: 자식 스레드의 새로운 값
    부모 스레드의 값: 부모 스레드의 값
 */