package algorithms.tasks;

import java.util.*;

public class DisjointedBeautifulPairs {

    public int solve(List<Integer> a, List<Integer> b) {
        int result = 0;
        Map<Integer, Integer> as = new HashMap<>();
        for (int x : a) {
            as.put(x, as.getOrDefault(x, 0) + 1);
        }
        Set<Integer> bs = new HashSet<>();
        for (int x : b) {
            bs.add(x);
            Integer ax = as.get(x);
            if (ax != null) {
                result++;
                if (ax > 1) {
                    as.put(x, ax - 1);
                } else {
                    as.remove(x);
                }
            }
        }

        if (result < b.size() && !as.isEmpty()) {
            result++;
        } else {
            result--;
        }
        return result;
    }
}
