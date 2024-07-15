package org.example.ch06.ex01;

public class Mutex {
    private boolean lock = false;

    public synchronized void acquired() {
        // 락 획득할 때까지 대기 -> 다른 스레드의 락이 해제되어야 함
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 락 획득
        this.lock = true;
    }

    public synchronized void release() {
        // 락 해제
        this.lock = false;

        // 대기중인 스레드를 깨우기
        this.notify();
    }
}
