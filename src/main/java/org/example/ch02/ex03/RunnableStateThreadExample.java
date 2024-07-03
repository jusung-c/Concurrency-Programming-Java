package org.example.ch02.ex03;

public class RunnableStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 1000000000; i++) {
                        if(i%1000000000 == 0){
                            System.out.println("스레드 상태: " + Thread.currentThread().getState());
                        }
                    }
                }
            }
        });

        thread.start();
    }
}

// 출력: 스레드 상태: RUNNABLE 반복
