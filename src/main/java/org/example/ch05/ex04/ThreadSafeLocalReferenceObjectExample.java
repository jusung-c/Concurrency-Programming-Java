package org.example.ch05.ex04;
/*
    스레드별로 new로 객체를 새로 생성하기 때문에 독립된 인스턴스를 가진다.
    각자 다른 객체가 힙에 생성되어 참조하는 것이므로 독립적으로 작동한다. (스레드 간 안전하다)

    만약 멤버 변수로 객체를 생성하고 그 하나의 객체를 여러 스레드가 공유하게 되기 때문에
    올바른 값이 나오지 않는다.
 */

public class ThreadSafeLocalReferenceObjectExample {
    class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject{" + "value: " + value + '}';
        }
    }

//    LocalObject localObject = new LocalObject();

    public void userLocalObject() {
        // 지역 객체 참조. 각 스레드는 이 객체의 독립된 인스턴스 가짐
        LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        Thread thread1 = new Thread(() -> {
            example.userLocalObject();
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.userLocalObject();
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}

/*
    Thread-2 - LocalObject{value: 1}
    Thread-1 - LocalObject{value: 1}
    Thread-2 - LocalObject{value: 2}
    Thread-1 - LocalObject{value: 2}
    Thread-2 - LocalObject{value: 3}
    Thread-1 - LocalObject{value: 3}
    Thread-2 - LocalObject{value: 4}
    Thread-1 - LocalObject{value: 4}
    Thread-2 - LocalObject{value: 5}
    Thread-1 - LocalObject{value: 5}
 */