package org.example.ch11.ex01;

public class AsyncNonBlocking {
    interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {
        // 비동기 작업 수행
        doAsyncWork(result -> {
            System.out.println("비동기 작업 결과: " + result);
        });

        System.out.println("메인 스레드는 다른 작업을 계속 수행할 수 있습니다.");
    }

    private static void doAsyncWork(Callback callback) {
        new Thread(() -> {
            try {
                System.out.println("비동기 작업 시작");
                Thread.sleep(2000);
                callback.onComplete(42); // 콜백 호출

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }).start();
    }
}
