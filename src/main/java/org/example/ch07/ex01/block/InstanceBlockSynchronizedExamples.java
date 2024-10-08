package org.example.ch07.ex01.block;

/*
    두 모니터 락이 다르므로 동기화 처리가 되지 않아서 원하는 결과가 나오지 않는다.

    모니터를 통일 시키면 원하는 결과를 얻을 수 있다.
 */
public class InstanceBlockSynchronizedExamples {

    private int count = 0;

    private Object lockObject = new Object();

    public void incrementBlockThis(){
        synchronized (this){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 This 에 의해 블록 동기화 함 : " + count);
        }
    }
    public void incrementBlockLockObject() {
        synchronized (lockObject){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 LockObject 에 의해 블록 동기화 함 : " + count);
        }
    }
    public static void main(String[] args){

        InstanceBlockSynchronizedExamples example = new InstanceBlockSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        },"스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        },"스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        },"스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        },"스레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값:"  + example.count);

    }
}
