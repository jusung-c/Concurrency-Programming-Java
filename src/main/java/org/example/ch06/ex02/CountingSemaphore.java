package org.example.ch06.ex02;

public class CountingSemaphore implements CommonSemaphore {

    private int signal;
    private int permits;

    public CountingSemaphore(int permits) {
        this.permits = permits;
        this.signal = permits;
    }

    @Override
    public void acquired() {
        synchronized (this) {
            while (this.signal == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.signal--;
        }

        System.out.println(Thread.currentThread().getName()
                + " 락 획득, 현재 세마포어 값: " + signal);
    }

    @Override
    public synchronized void release() {

        // signal이 permits 보다 작을 때만 증가
        if (this.signal < permits) {
            this.signal++;

            System.out.println(Thread.currentThread().getName()
                    + " 락 해제, 현재 세마포어 값: " + signal);

            notifyAll();
        }
    }
}
