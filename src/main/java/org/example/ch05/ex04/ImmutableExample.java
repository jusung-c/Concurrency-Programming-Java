package org.example.ch05.ex04;

/*
    클래스 자체를 아예 불변으로 만들어서(final class) 동시성 문제를 방지한다.
 */

public class ImmutableExample implements Runnable {
    private ImmutablePerson person;

    public ImmutableExample(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 이름: " + person.getName() + ", 나이: " + person.getAge());
    }

    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("홍길동", 25);

        for (int i = 0; i < 10; i++) {
            new Thread(new ImmutableExample(person)).start();
        }
    }
}

final class ImmutablePerson {
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
