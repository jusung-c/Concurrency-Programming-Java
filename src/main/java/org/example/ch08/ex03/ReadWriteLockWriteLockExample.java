package org.example.ch08.ex03;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        BankAccount account = new BankAccount(lock, 10000);

        // 읽기 쓰레드 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance();
            System.out.println("현재 잔액: " + balance);
        }).start();

        // 쓰기 쓰레드 출금
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int withdrawAmount = (int) (Math.random() * 1000);
                account.withdraw(withdrawAmount);
                System.out.println("출금: " + withdrawAmount);
            }).start();
        }

        // 읽기 쓰레드 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance();
            System.out.println("현재 잔액: " + balance);
        }).start();
    }
}
