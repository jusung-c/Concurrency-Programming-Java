package org.example.ch11.ex02;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executor.submit(new Service1());
        Future<Integer> future2 = executor.submit(new Service2(future1));
        Future<Integer> future3 = executor.submit(new Service3(future2));
        Future<Integer> future4 = executor.submit(new Service4(future3));
        Future<Integer> future5 = executor.submit(new Service5(future4));

        int finalResult = future5.get();

        executor.shutdown();

        System.out.println("finalResult: " + finalResult);
    }

    private static class Service1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Service 1 시작");
            return 1;
        }
    }

    private static class Service2 implements Callable<Integer> {
        private Future<Integer> future;

        public Service2(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 2 시작");
            return future.get() + 2;
        }
    }

    private static class Service3 implements Callable<Integer> {
        private Future<Integer> future;

        public Service3(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 3 시작");
            return future.get() * 3;
        }
    }

    private static class Service4 implements Callable<Integer> {
        private Future<Integer> future;

        public Service4(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 4 시작");
            return future.get() - 4;
        }
    }

    private static class Service5 implements Callable<Integer> {
        private Future<Integer> future;

        public Service5(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 5 시작");
            return future.get() + 5;
        }
    }
}