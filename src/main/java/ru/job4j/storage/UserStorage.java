package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = map.get(fromId);
        User userTo = map.get(toId);
        if (userTo != null && userFrom != null) {
            int userFromNewAmount = userFrom.getAmount() - amount;
            if (userFromNewAmount >= 0) {
                userFrom.setAmount(userTo.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
            }
        }
    }
}
