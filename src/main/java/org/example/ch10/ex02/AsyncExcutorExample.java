package org.example.ch10.ex02;

import java.util.concurrent.Executor;

public class AsyncExcutorExample {
    public static void main(String[] args) {
        Executor asyncExecutor = new AsyncExecutor();

        asyncExecutor.execute(()->{
            System.out.println("비동기 작업 1 수행 중...");
            // 작업 수행
            System.out.println("비동기 작업 1 완료...");
        });

        asyncExecutor.execute(()->{
            System.out.println("비동기 작업 2 수행 중...");
            // 작업 수행
            System.out.println("비동기 작업 2 완료...");
        });
    }

    static class AsyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }
}
