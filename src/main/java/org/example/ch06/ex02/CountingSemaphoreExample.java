package org.example.ch06.ex02;

public class CountingSemaphoreExample {
    public static void main(String[] args) {

        // 최대 10개의 스레드가 동시 작업할 수 있도록 설정
        int permits = 10;
        CountingSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource resource = new SharedResource(semaphore);

        // 전체 스레드 개수
        int threadCount = 15;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {

            // 여러 스레드 접근이 허용되므로 원하는 값이 나오지는 않는다.
            // 동기화 처리를 해주면 원하는 값을 얻을 수 있다.
            threads[i] = new Thread(resource::sum);

            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("최종 값: " + resource.getSum());
    }
}
