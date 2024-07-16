package org.example.ch06.ex02;

public class BinarySemaphoreExample {

    public static void main(String[] args) {

        CommonSemaphore semaphore = new BinarySemaphore();
        SharedResource resource = new SharedResource(semaphore);

        Thread thread1 = new Thread(resource::sum);
        Thread thread2 = new Thread(resource::sum);

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 값: " + resource.getSum());
    }
}

class SharedResource {
    private int value = 0;
    private CommonSemaphore commonSemaphore;

    public SharedResource(CommonSemaphore commonSemaphore) {
        this.commonSemaphore = commonSemaphore;
    }

    public void sum() {
        commonSemaphore.acquired();

        // critical section
        for (int i = 0; i < 100_0000; i++) {
            value++;
        }

        commonSemaphore.release();
    }

    public int getSum() {
        return value;
    }
}

