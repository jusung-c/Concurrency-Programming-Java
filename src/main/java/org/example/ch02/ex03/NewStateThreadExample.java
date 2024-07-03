package org.example.ch02.ex03;

public class NewStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        // 실행을 하지 않고 상태 보기
        System.out.println("스레드 상태: " + thread.getState());
    }
}

// 출력: 스레드 상태: NEW
