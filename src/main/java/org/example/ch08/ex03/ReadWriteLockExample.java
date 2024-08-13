package org.example.ch08.ex03;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    static class SharedData{
        private int data = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedData sharedData = new SharedData();

        Thread reader1 = new Thread(() -> {
            readWriteLock.readLock().lock();

            try {
                System.out.println("읽기 스레드 1이 데이터를 읽는 중..: " + sharedData.getData());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            readWriteLock.readLock().lock();

            try {
                System.out.println("읽기 스레드 2이 데이터를 읽는 중..: " + sharedData.getData());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            readWriteLock.writeLock().lock();

            try {
                System.out.println("쓰기 스레드가 데이터를 쓰는 중..");
                sharedData.setData(40);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("변경한 데이터: " + sharedData.getData());
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        reader1.start();
        reader2.start();
        writer.start();

    }
}
/*
    읽기 스레드 2이 데이터를 읽는 중..: 0
    읽기 스레드 1이 데이터를 읽는 중..: 0
    쓰기 스레드가 데이터를 쓰는 중..
    변경한 데이터: 40
 */