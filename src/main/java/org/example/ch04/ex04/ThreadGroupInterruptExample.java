package org.example.ch04.ex04;

public class ThreadGroupInterruptExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            while (true) {
                System.out.println("상위 스레드 그룹 실행 중..");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "상위 그룹 스레드");

        Thread subGroupThread = new Thread(subGroup, () -> {
            while (true) {
                System.out.println("하위 스레드 그룹 실행 중..");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "하위 그룹 스레드");


        topGroupThread.start();
        subGroupThread.start();

        Thread.sleep(3000);

        System.out.println("그룹 스레드를 중지");

        subGroup.interrupt();
        topGroup.interrupt();
    }
}

/*
    ..
    상위 스레드 그룹 실행 중..
    하위 스레드 그룹 실행 중..
    상위 스레드 그룹 실행 중..
    하위 스레드 그룹 실행 중..
    그룹 스레드를 중지
    Exception in thread "하위 그룹 스레드" Exception in thread "상위 그룹 스레드" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
        at org.example.ch04.ex04.ThreadGroupInterruptExample.lambda$main$1(ThreadGroupInterruptExample.java:26)
        at java.base/java.lang.Thread.run(Thread.java:833)
    Caused by: java.lang.InterruptedException: sleep interrupted
        at java.base/java.lang.Thread.sleep(Native Method)
        at org.example.ch04.ex04.ThreadGroupInterruptExample.lambda$main$1(ThreadGroupInterruptExample.java:24)
        ... 1 more
    java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
        at org.example.ch04.ex04.ThreadGroupInterruptExample.lambda$main$0(ThreadGroupInterruptExample.java:15)
        at java.base/java.lang.Thread.run(Thread.java:833)
    Caused by: java.lang.InterruptedException: sleep interrupted
        at java.base/java.lang.Thread.sleep(Native Method)
        at org.example.ch04.ex04.ThreadGroupInterruptExample.lambda$main$0(ThreadGroupInterruptExample.java:13)
        ... 1 more
 */
