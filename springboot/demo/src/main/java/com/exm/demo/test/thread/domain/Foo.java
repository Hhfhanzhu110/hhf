package com.exm.demo.test.thread.domain;

public class Foo {

    public void first(Runnable printFirst) {
        printFirst.run();
    }

    public void second(Runnable printSecond) {
        printSecond.run();
    }

    public void third(Runnable printThird) {
        printThird.run();
    }
}
