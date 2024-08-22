package org.example.ch10.ex01;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        // 3개의 스레드를 보유한 스레드 풀 생성
        SimpleThreadPool threadPool = new SimpleThreadPool(3);

        // 작업을 스레드 풀에 submit
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;

            threadPool.submit(() -> {
                // 작업 내용
                System.out.println("작업 " + taskId + " 수행 중...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("작업 " + taskId + " 완료.");
            });
        }

        Thread.sleep(3000);

        // 스레드 풀 종료
        threadPool.shutdown();
    }
}
