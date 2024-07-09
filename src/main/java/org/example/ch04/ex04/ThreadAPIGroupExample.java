package org.example.ch04.ex04;

public class ThreadAPIGroupExample {
    public static void main(String[] args) {
        ThreadGroup topGroup = new ThreadGroup("상위그룹");

        Thread[] topThreads = new Thread[5];
        for (int i = 1; i <= 5; i++) {
            topThreads[i-1]  = new Thread(topGroup, () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "스레드" + i);
            topThreads[i-1].start();
        }

        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        Thread[] subThreads = new Thread[5];
        for (int i = 6; i <= 10; i++) {
            subThreads[i-6] = new Thread(subGroup, () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "스레드" + i);
            subThreads[i-6].start();
        }

        /* 모든 스레드가 종료될 때까지 기다림
            for (Thread thread : topThreads) {
                thread.join();
            }
            for (Thread thread : subThreads) {
                thread.join();
            }
        */

        System.out.println("상위그룹 활성 스레드 수: " + topGroup.activeCount());
        System.out.println("상위그룹 활성 스레드 그룹 수: " + topGroup.activeGroupCount());

        System.out.println("하위그룹 활성 스레드 수: " + subGroup.activeCount());
        System.out.println("하위그룹 활성 스레드 그룹 수: " + subGroup.activeGroupCount());

        System.out.println("하위그룹의 부모 그룹: " + subGroup.getParent().getName());

        System.out.println("상위그룹은 하위그룹의 부모 또는 조상 그룹인가? " + topGroup.parentOf(subGroup));
    }
}

/*
    상위그룹 활성 스레드 수: 10
    상위그룹 활성 스레드 그룹 수: 1
    하위그룹 활성 스레드 수: 5
    하위그룹 활성 스레드 그룹 수: 0
    하위그룹의 부모 그룹: 상위그룹
    상위그룹은 하위그룹의 부모 또는 조상 그룹인가? true
 */
