package org.example.ch04.ex05.logger;

public class LogWorker implements Runnable {

    @Override
    public void run() {

        // 서비스 생성
        ThreadLocalLogger.ServiceA serviceA = new ThreadLocalLogger.ServiceA();
        ThreadLocalLogger.ServiceB serviceB = new ThreadLocalLogger.ServiceB();
        ThreadLocalLogger.ServiceC serviceC = new ThreadLocalLogger.ServiceC();

        if (Thread.currentThread().getName().equals("Thread-1")) {
            serviceA.process();
            serviceB.process();
            serviceC.process();
        } else if (Thread.currentThread().getName().equals("Thread-2")) {
            serviceB.process();
            serviceA.process();
            serviceC.process();
        } else {
            serviceC.process();
            serviceA.process();
            serviceB.process();
        }

        // 출력하고 삭제
        ThreadLocalLogger.printLog();
        ThreadLocalLogger.clearLog();
    }
}
