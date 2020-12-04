package algorithms.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class QHeap1 {

    int[] heap;
    Set<Integer> added = new HashSet<>();
    int size = 0;
    boolean minified = true;

    public void add(int i) {
        if (!added.add(i)) {
            return;
        }
        heap[size] = i;
        int current = size++;
        minified = false;
        while (heap[current] < heap[parent(current)]) {
            current = swap(current, parent(current));
        }
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public int swap(int i1, int i2) {
        int value = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = value;
        return i2;
    }

    private boolean hasChildren(int i) {
        return i < size / 2;
    }

    public void delete(int value) {
        added.remove(value);
        if (!minified) {
            minify(0);
        }
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                deleteInt(i);
            }
        }
    }

    private void deleteInt(int i) {
        if (size == 1) {
            size = 0;
            return;
        }
        heap[i] = heap[--size];
        minify(i);
    }

    private int left(int i) {
        return i * 2 + 1;
    }

    private int right(int i) {
        return i * 2 + 2;
    }

    private void minify(int i) {
        if (!hasChildren(i)) {
            return;
        }
        if (heap[i] > heap[left(i)] || right(i) < size && heap[i] > heap[right(i)]) {
            if (right(i) >= size || heap[left(i)] < heap[right(i)]) {
                swap(i, left(i));
                minify(left(i));
            } else {
                swap(i, right(i));
                minify(right(i));
            }
        }
        minified = true;
    }

    public String min() {
        if (size > 0) {
            if (!minified) {
                minify(0);
            }
            return String.valueOf(heap[0]);
        } else {
            return "";
        }
    }

    public void print() {
        String result = Arrays.toString(heap);
    }


    public static void main(String[] args) {
        new QHeap1().parse();
    }


    public void parse() {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine().trim());
        heap = new int[100_000];
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().trim().split("\\s");
            if (line[0].equals("1")) {
                add(Integer.parseInt(line[1]));
            } else if (line[0].equals("2")) {
                delete(Integer.parseInt(line[1]));
            } else {
                System.out.println(min());
            }
        }
    }

}
