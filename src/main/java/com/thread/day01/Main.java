package com.thread.day01;

public class Main {
    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        new Thread(threadStyle).start();

        Thread02 thread02 = new Thread02();
        thread02.start();
    }
}
