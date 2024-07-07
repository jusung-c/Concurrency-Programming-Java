package org.example.ch03.ex03;

public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("스레드 작동 중..");

                if(Thread.interrupted()) {
                    System.out.println("인터럽트 상태가 초기화 되었습니다.");
                    break;
                }
            }

            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());

            // false로 초기화되었기 때문에 true로 원복을 해줘야 해당 스레드를 참조로 가진 스레드에 발생하는 사이드 이펙트를 막을 수 있다.
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

/*
    스레드 작동 중..
    스레드 작동 중..
    인터럽트 상태가 초기화 되었습니다.
    인터럽트 상태: false
    인터럽트 상태: true
 */