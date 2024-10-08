package org.example.ch10.ex11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PrestartThreadsExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue =  new LinkedBlockingQueue<>();
        int taskNum = 9;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        executor.prestartAllCoreThreads();      // 코어 스레드를 미리 시작

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 태스크" + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();
    }
}
