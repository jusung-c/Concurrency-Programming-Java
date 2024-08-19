package org.example.ch09.ex03;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterAPIExample {
    static AtomicIntegerFieldUpdater<MyClass> fieldUpdater1;
    static AtomicReferenceFieldUpdater<MyClass, String> fieldUpdater2;

    public static class MyClass {
        private volatile int field1;
        private volatile String field2;

        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }

    public static void main(String[] args) {
        fieldUpdater1 = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "field1");
        fieldUpdater2 = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "field2");

        MyClass instance = new MyClass();

        fieldUpdater1.addAndGet(instance, 42);
        fieldUpdater2.compareAndSet(instance, null, "myField");

        System.out.println("update value: " + instance.getField1());
        System.out.println("update value: " + instance.getField2());

    }

}
