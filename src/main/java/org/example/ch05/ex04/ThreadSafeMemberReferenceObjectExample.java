package org.example.ch05.ex04;

/*
    Company를 각자 생성해서 사용하면 스레드 안전
    이미 생성된 하나의 Companry 멤버 변수를 공유하면 스레드 불안전

    따라서 각각의 스레드에서 new로 생성해서 스레드가 안전한 것이다.

    만약 하나의 Company 객체를 사용하더라도 synchronized에서 그 멤버 변수를 사용하지 않고
    지역 변수나 매개변수만 사용하도록 하면 스레드 안전할테지만 여기서는 멤버 변수를 사용하기 때문에 스레드 안전하지 않은 것이다.

    그냥 모든 객체를 다 new 해서 스레드마다 자신의 객체를 바라보도록 하는 게 좋다.
 */

public class ThreadSafeMemberReferenceObjectExample {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyRunnable(new Company("Company"))).start(); // 스레드에 안전함, 멤버변수를 공유하지 않음
        new Thread(new MyRunnable(new Company("Company"))).start(); // 스레드에 안전함, 멤버변수를 공유하지 않음

        Thread.sleep(1000);
        System.out.println("============================================================");

        Company company = new Company("Company"); // 스레드에 안전하지 못함, 멤버변수를 공유함
        new Thread(new MyRunnable(company)).start();
        new Thread(new MyRunnable(company)).start();
    }
}

class MyRunnable implements Runnable {
    private Company company;

    public MyRunnable(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        company.changeName(Thread.currentThread().getName());
    }
}

class Company {
    private Member member;

    public Company(String name) {
        this.member = new Member(name);
    }

    public synchronized void changeName(String name) {
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + ": " + member.getName());
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/*
    Company: Thread-0
    Company: Thread-1
    ============================================================
    Company: Thread-2
    Thread-2: Thread-3
 */