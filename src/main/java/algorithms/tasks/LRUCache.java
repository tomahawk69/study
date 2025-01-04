package algorithms.tasks;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private int size = 0;
    private Node head, tail;
    private final Map<Integer, Node> values = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer result = moveFirst(values.get(key));
        return result != null ? result : -1;
    }

    public void put(int key, int value) {
        Node current = values.get(key);
        if (current != null) {
            current.value = value;
            moveFirst(current);
        } else {
            Node node = new Node(key, value);
            values.put(key, node);
            node.next = head;
            if (tail == null) {
                tail = node;
            }
            if (head != null) {
                head.prev = node;
            }
            head = node;
            size++;
        }
        if (size > capacity) {
            Node prev = tail.prev;
            tail.prev = null;
            prev.next = null;
            values.remove(tail.key);
            tail = prev;
            size--;
        }
    }


    private Integer moveFirst(Node node) {
        if (node == null) {
            return null;
        }
        if (node != head) {
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node.value;
    }


    private static class Node {
        int key;
        int value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

}
