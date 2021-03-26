package com.github.jooq.example.service;

public class ExampleService {
    public int add(int a, int b) {
        return a + b;
    }


    public int publicSub(int a, int b) {
        return sub(a, b);
    }


    private int sub(int a, int b) {
        System.out.println("进入私有方法调用");
        return a - b;
    }

    public static int multi(int a, int b) {
        return a * b;
    }
}
