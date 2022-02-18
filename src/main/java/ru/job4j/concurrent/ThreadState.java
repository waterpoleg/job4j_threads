package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> { });
        Thread second = new Thread(() -> { });
        System.out.println(first.getName() + " - " + first.getState());
        first.start();
        System.out.println(second.getName() + " - " + second.getState());
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("working...");
        }
        System.out.println("finished");
    }
}
