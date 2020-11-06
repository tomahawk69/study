package algorithms.tasks;

import java.util.HashSet;
import java.util.Set;

public class ComponentsInGraph {

    static int[] componentsInGraph(int[][] gb) {
        Set<Integer>[] data = new Set[gb.length * 2];
        for (int i = 0; i < data.length; i++) {
            data[i] = new HashSet<>();
            data[i].add(i);
        }

        for (int[] ints : gb) {
            int a = ints[0] - 1;
            int b = ints[1] - 1;
            if (data[a] != data[b]) {
                int a1 = a;
                int b1 = b;
                if (data[a].size() < data[b].size()) {
                    a1 = b;
                    b1 = a;
                }
                if (data[a1].addAll(data[b1])) {
                    for (int c : data[b1]) {
                        if (data[a1] != data[c]) {
                            data[c] = data[a1];
                        }
                    }
                }
            }
        }

        int min = data.length;
        int max = 0;
        for (int i = 0; i < gb.length; i++) {
            int size = data[i].size();
            if (size > 1 && size > max) {
                max = size;
            }
            if (size > 1 && size < min) {
                min = size;
            }
        }
        int[] result = new int[2];
        result[0] = min;
        result[1] = max;
        return result;
    }

}
