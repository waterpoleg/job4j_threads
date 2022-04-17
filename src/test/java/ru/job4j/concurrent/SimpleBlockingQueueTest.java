package ru.job4j.concurrent;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOneConsumerAndOneProducer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    queue.poll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 4; i++) {
                    queue.offer(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertEquals(2, queue.size());
    }
}