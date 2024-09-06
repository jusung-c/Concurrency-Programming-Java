package org.example.ch10.ex04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
    1. 메인 스레드가 ExecutorService를 설정하고 태스크를 제출합니다.
    2. ExecutorService 스레드가 태스크 실행을 시작합니다 (1초 대기).
    3. 메인 스레드가 콜백을 등록하고 새 스레드를 시작합니다.
    4. 콜백 처리 스레드가 Future.get()으로 결과를 기다립니다.
    5. ExecutorService 스레드가 태스크를 완료하고 결과를 반환합니다.
    6. 콜백 처리 스레드가 결과를 받아 콜백 함수를 실행합니다.
 */

public class FutureCallbackExample {
    interface Callback {

        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        System.out.println("비동기 작업 시작");

        registerCallback(future, result -> {
            System.out.println("비동기 작업 결과: " + result);
        });

        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            int result;

            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            callback.onComplete(result);
        }).start();
    }

}
