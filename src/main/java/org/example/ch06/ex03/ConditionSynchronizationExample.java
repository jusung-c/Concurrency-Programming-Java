package org.example.ch06.ex03;

public class ConditionSynchronizationExample {
    private boolean isAvailable = false;

    public synchronized void produce() {
        while (isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("생산 완료");
        isAvailable = true;

        notify();
    }

    public synchronized void consume() {
        while (!isAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("소비 완료");
        isAvailable = false;

        notify();
    }

    public static void main(String[] args) {
        ConditionSynchronizationExample example = new ConditionSynchronizationExample();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.produce();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.consume();
            }
        }).start();
    }
}

/*
생산 완료
소비 완료
생산 완료
소비 완료
생산 완료
소비 완료
생산 완료
소비 완료
생산 완료
소비 완료
 */