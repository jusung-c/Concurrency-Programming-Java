package org.example.ch05.ex03;

public class NonRaceConditionExample {
    private static int sharedResource = 0;

    public static void main(String[] args) throws InterruptedException {

        // 스레드 100개를 생성해 공유 리소스를 동시에 증가시킨다
        Thread[] incrementThreads = new Thread[100];
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (NonRaceConditionExample.class) {
                        sharedResource++; // 공유 데이터 동시 접속 불가능
                    }
                }
            });
            incrementThreads[i].start();
        }

        // 모든 스레드가 작업을 완료할 때까지 대기
        for (Thread t : incrementThreads) {
            t.join();
        }

        System.out.println("Expected Value: " + (100 * 10000));
        System.out.println("Actual value: " + sharedResource);
    }
}

/*
    Expected Value: 1000000
    Actual value: 1000000
 */
