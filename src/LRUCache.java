import java.util.*;

public class LRUCache {

    //store keys of cache
    private Deque<Integer> queue;

    //store reference of key in cache
    private HashMap<Integer, Integer> map;

    //maximum capacity of cache
    private final int capacity;

    public LRUCache(int capacity) {
        this.queue = new LinkedList<>();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);
        if(value == null) return -1;
        moveToFront(key);
        return value;
    }

    private void moveToFront(int key) {
        queue.remove(key);
        queue.offerFirst(key);
    }

    public void put(int key, int value) {
        Integer oldValue = map.get(key);
        if(oldValue != null) {
            map.put(key, value);
            moveToFront(key);
            return;
        }

        if(queue.size() == capacity) {
            Integer lastKey = queue.pollLast();
            map.remove(lastKey);
        }

        queue.offerFirst(key);
        map.put(key, value);
    }


}
