package com.exm.demo.test.thread.test;

import com.exm.demo.test.thread.domain.Foo;

public class TestThread {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread fthread = new Thread(() -> {
            try {
                foo.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread sthread = new Thread(() -> {
            try {
                foo.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread tthread = new Thread(() -> {
            try {
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fthread.start();
        sthread.start();
        tthread.start();
    }
}
