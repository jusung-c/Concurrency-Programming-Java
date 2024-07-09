package org.example.ch04.ex04;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        Thread defualtGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");
        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");
        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "CustomGroupThread");

        defualtGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + "에 속한다.");

        }
    }

}

/*
    CustomGroupThread 는 Custom Thread Group에 속한다.
    DefaultGroupThread 는 main에 속한다.
    MainGroupThread 는 main에 속한다.
 */
