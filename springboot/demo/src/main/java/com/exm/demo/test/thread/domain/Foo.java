package com.exm.demo.test.thread.domain;

import java.util.concurrent.Semaphore;

public class Foo {
    private Semaphore first = new Semaphore(0);
    private Semaphore second = new Semaphore(0);

    public void first() throws InterruptedException {
        System.out.println("first");
        first.release();
    }

    public void second() throws InterruptedException {
        first.acquire();
        System.out.println("second");
        second.release();
    }

    public void third() throws InterruptedException {
        second.acquire();
        System.out.println("thread");
    }
}
