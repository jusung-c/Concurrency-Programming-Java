package org.example.ch08.ex03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class BankAccount {
    private final String accountName = "account1";
    private final ReadWriteLock lock;
    private Map<String, Integer> balance;


    public BankAccount(ReadWriteLock lock, int amount) {
        this.lock = lock;
        balance = new HashMap<>();
        balance.put(accountName, amount);
    }

    public int getBalance() {
        lock.readLock().lock();

        try {
            Thread.sleep(1000);
            return balance.get(accountName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void deposit(int amount) {
        lock.writeLock().lock();

        try {
            Thread.sleep(2000);

            int currentBalance = balance.get(accountName);
            currentBalance += amount;
            balance.put(accountName, currentBalance);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw(int amount) {
        lock.writeLock().lock();

        try {
            int currentBalance = balance.get(accountName);

            if (currentBalance >= amount) {
                currentBalance -= amount;
                balance.put(accountName, currentBalance);
                System.out.println(Thread.currentThread().getName() + " - 출금 성공");
            } else {
                System.out.println(Thread.currentThread().getName() + " - 출금 실패, 잔액 부족");
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
