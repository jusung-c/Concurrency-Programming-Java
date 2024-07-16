package org.example.ch06.ex02;

public class BinarySemaphore implements CommonSemaphore{
    private int signal = 1;

    @Override
    public synchronized void acquired() {
        while (this.signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // 현재 스레드의 인터럽트 상태를 설정
                Thread.currentThread().interrupt();
            }
        }

        // 신호 변경 (사용중)
        this.signal = 0;
    }

    @Override
    public synchronized void release() {
        // 신호 변경 (사용가능)
        this.signal = 1;
        this.notify();
    }

}
