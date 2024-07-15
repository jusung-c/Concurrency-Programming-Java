package org.example.ch06.ex01;

public class SharedData {
    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            // Lock 획득
            mutex.acquired();

            // Critical Section
            for (int i = 0; i < 10_000_0000; i++) {
                sharedValue++;
            }

        // 반드시 finally에서 해줘야 에러 발생 시에도 release() 처리 가능
        } finally {
            // Lock 해제
            mutex.release();
        }
    }

    public int getSum() {
        return sharedValue;
    }
}
