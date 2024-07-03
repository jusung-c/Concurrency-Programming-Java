package org.example.ch02.ex02;

/*
    * 각 쓰레드는 독립적으로 실행되며 자신의 호출 스택을 가지고 있다. (독립적인 로컬 변수 관리)
    * objectReference의 경우 변수 자체는 로컬 변수이지만 String 타입은 객체이기 때문에 힙에 저장된다.
    * 따라서 참조는 로컬 변수에, 실제 객체는 힙에 저장되는 것
        -> 힙? 힙이면 공유되는 영역 아닌가?
            * 객체가 힙에 저장된다는 사실만으로는 스레드 간 공유된다는 것을 의미하지 않음
            * 객체의 참조를 여러 스레드가 공유할 때만 해당 객체가 스레드 간 공유되는 것
            * 여기 예제에서는 스레드마다 새로 String을 생성하므로 공유되지 않음
            * 참고) String은 불변 객체라서 공유되더라도 상태가 변경되지 않아서 스레드 안정성 유지 가능
 */

public class ThreadStackExample {
    public static void main(String[] args) {
        // 3개의 스레드를 생성하고 시작
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));

            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {

            int localValue = threadId + 100;
            secondMethod(localValue);

        }

        private void secondMethod(int localValue) {
            String objectReference = threadId + "Hello World";
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID: " + threadId + ", Value: " + localValue);
        }
    }
}

/*
        Thread-2 : 스레드 실행 중..
        Thread-0 : 스레드 실행 중..
        Thread-1 : 스레드 실행 중..
        Thread-0 : 스레드 ID: 0, Value: 100
        Thread-2 : 스레드 ID: 2, Value: 102
        Thread-1 : 스레드 ID: 1, Value: 101
 */