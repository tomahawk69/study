package algorithms.tasks;

import java.util.*;

public class PriyankaAndToys {

    public int solve(List<Integer> weights) {
        int result = 0;
        Map<Integer, Integer> counts = new TreeMap<>();
        Set<Integer> existed = new HashSet<>();
        for (int weight : weights) {
            existed.add(weight);
            for (int j = 0; j < 4; j++) {
                if (weight - j < 0) break;
                counts.compute(weight - j, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            }
        }
        int prev = -1;
        for (int weight : counts.keySet()) {
            if (!existed.contains(weight)) {
                continue;
            }
            if (weight < prev) {
                continue;
            }
            result++;
           prev = weight + 5;
        }

        return result;
    }
}
