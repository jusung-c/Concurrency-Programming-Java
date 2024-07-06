package org.example.ch03.ex02;

/*
    1. 메인 스레드와 longRunningThread, interruptingThread 스레드를 생성하고 시작한다.
    2. interruptingThread가 2초 후에 longRunningThread를 인터럽트한다.
    3. longRunningThread는 인터럽트를 감지하고, catch 블록에서 mainThread를 인터럽트한다.
    4. longRunningThread는 인터럽트 되었다는 메시지를 출력하고 종료된다.
    5. 메인 스레드는 longRunningThread가 종료되기를 join()으로 기다리다가, longRunningThread가 인터럽트되면서 mainThread를 인터럽트하게 되어 InterruptedException을 던진다.
    6. InterruptedException을 잡은 메인 스레드는 인터럽트 되었다는 메시지를 출력하고 예외를 던진다.
 */

public class InterruptedJoinExample {
    public static void main(String[] args) {

        // 현재 수행중인 스레드 정보 얻기
        Thread mainThread = Thread.currentThread();

        Thread longRunningThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("longRunningThread가 계속 실행 중 ..");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                mainThread.interrupt(); // 대상 스레드에서 메인 스레드에 인터럽트
                System.out.println("longRunningThread가 인터럽트 되었습니다.");
            }
        });

        longRunningThread.start();

        Thread interruptingThread = new Thread(() -> {
            try {

                System.out.println("인터럽트 스레드가 2초 후에 longRunningThread를 인터럽트 한다.");
                Thread.sleep(2000);
                longRunningThread.interrupt();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        interruptingThread.start();

        try {
            System.out.println("메인 스레드가 longRunningThread의 완료를 기다린다.");
            longRunningThread.join();
            System.out.println("메인 스레드 작업 완료");

        } catch (InterruptedException e) {
            System.out.println("메인 스레드가 인터럽트 되었습니다.");
            throw new RuntimeException(e);
        }

    }
}

/*
    longRunningThread가 계속 실행 중 ..
    메인 스레드가 longRunningThread의 완료를 기다린다.
    인터럽트 스레드가 2초 후에 longRunningThread를 인터럽트 한다.
    longRunningThread가 계속 실행 중 ..
    longRunningThread가 인터럽트 되었습니다.
    메인 스레드가 인터럽트 되었습니다.
    Exception in thread "main" java.lang.RuntimeException: java.lang.InterruptedException
        at org.example.ch03.ex02.InterruptedJoinExample.main(InterruptedJoinExample.java:53)
    Caused by: java.lang.InterruptedException
        at java.base/java.lang.Object.wait(Native Method)
        at java.base/java.lang.Thread.join(Thread.java:1304)
        at java.base/java.lang.Thread.join(Thread.java:1372)
        at org.example.ch03.ex02.InterruptedJoinExample.main(InterruptedJoinExample.java:48)
 */