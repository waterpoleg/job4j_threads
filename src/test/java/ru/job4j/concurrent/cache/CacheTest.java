package ru.job4j.concurrent.cache;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        assertTrue(cache.add(base));
        assertFalse(cache.add(base));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        cache.delete(base);
        assertTrue(cache.add(base));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        cache.delete(base);
        assertTrue(cache.add(base));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateException() {
        Cache cache = new Cache();
        Base baseOne = new Base(1, 0);
        Base baseTwo = new Base(1, 1);
        baseOne.setName("Base1");
        baseTwo.setName("Base2");
        cache.add(baseOne);
        cache.update(baseTwo);
    }
}
