package org.example.ch04.ex04;

public class ThreadGroupScopeExample {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("최상위 스레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹");

        // 상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정
        Thread topGroupThread = new Thread(topGroup, () -> {
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            subGroup.setMaxPriority(7);  // 원래 최대 우선순위는 디폴트로 10
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "상위 스레드 그룹");

        // 하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정
        Thread subGroupThread = new Thread(subGroup, () -> {
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : " + topGroup.getMaxPriority());
            topGroup.setMaxPriority(4);  // 원래 최대 우선순위는 디폴트로 10
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : " + topGroup.getMaxPriority());
        }, "하위 스레드 그룹");

        topGroupThread.start();
        subGroupThread.start();

        topGroupThread.join();
        subGroupThread.join();

        // 런타임에 우선순위가 실시간으로 변경되는지 확인
        System.out.println(topGroupThread.getName() + " : " + topGroupThread.getPriority());
        System.out.println(subGroupThread.getName() + " : " + subGroupThread.getPriority());

        // 확인해보면 변경되지 않음을 알 수 있었다. -> 스레드가 이미 시작되었으면 변경되지 않음

        Thread userThread1 = new Thread(topGroup, () -> {}, "유저스레드 1");
        Thread userThread2 = new Thread(topGroup, () -> {}, "유저스레드 2");

        userThread1.start();
        userThread2.start();

        // maxPriority를 넘길 수 없다.
        userThread1.setPriority(10);
        userThread2.setPriority(10);

        userThread1.join();
        userThread2.join();

        // 우선순위 변경 후에 새롭게 생성한 스레드에는 적용된다.
        System.out.println(userThread1.getName() + " : " + userThread1.getPriority());

        // 그런데 하위 스레드는 변경한 7이 아닌 상위 스레드 우선순위인 4로 출력된다
        // -> 스레드 그룹이 중첩될 경우 최상위 스레드 그룹의 우선순위로 적용된다.
        System.out.println(userThread2.getName() + " : " + userThread2.getPriority());

    }
}

/*
    상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : 10
    하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : 10
    상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : 7
    하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : 4
    상위 스레드 그룹 : 5
    하위 스레드 그룹 : 5
    유저스레드 1 : 4
    유저스레드 2 : 4
 */
