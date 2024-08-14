package org.example.ch09.ex02;

public class NonAtomicIntegerGetAndUpdateExample {
    private static int accountBalance = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 100;
                int updatedBalance = 0;

                synchronized (NonAtomicIntegerGetAndUpdateExample.class) {
                    if (accountBalance >= withdrawalAmount) {
                        updatedBalance = accountBalance - withdrawalAmount;
                        accountBalance = updatedBalance;
                    }
                }

                if (updatedBalance <= 0) {
                    System.out.println("잔고 부족으로 출금 실패");
                } else {
                    System.out.println("출금 후 잔고: " + updatedBalance);
                }
            }).start();
        }
    }
}
