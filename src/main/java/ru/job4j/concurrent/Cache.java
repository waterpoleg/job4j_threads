package ru.job4j.concurrent;

public class Cache {
    public static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            return new Cache();
        }
        return cache;
    }
}
