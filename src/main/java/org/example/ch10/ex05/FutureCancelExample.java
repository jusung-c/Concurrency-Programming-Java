package org.example.ch10.ex05;

import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Callable 작업
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            Thread.sleep(2000);
            System.out.println("비동기 작업 완료!!!");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        Thread.sleep(1000);

        future.cancel(true);
//        future.cancel(false);

        // isCancelled로 예외 막기
        if (!future.isCancelled()) {
            try {
                int result = future.get();
                System.out.println("result = " + result);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (CancellationException e) {
                System.out.println("e = " + e);
            }
        } else {
            System.out.println("작업이 취소되었습니다.");
        }



        executorService.shutdown();
    }
}
