package algorithms.tasks;

import org.junit.jupiter.api.Test;

class LRUCacheTest {

    @Test
    void shouldPopulateAndQueryCacheCase1() {
        int[][] input = {{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
        LRUCache lruCache = new LRUCache(2);
        for (int[] a : input) {
            if (a.length == 1) {
                lruCache.get(a[0]);
            } else {
                lruCache.put(a[0], a[1]);
            }
        }
    }

    @Test
    void shouldPopulateAndQueryCacheCase2() {
        int[][] input = {{2}, {1, 0}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
        LRUCache lruCache = new LRUCache(2);
        for (int[] a : input) {
            if (a.length == 1) {
                lruCache.get(a[0]);
            } else {
                lruCache.put(a[0], a[1]);
            }
        }
    }
}