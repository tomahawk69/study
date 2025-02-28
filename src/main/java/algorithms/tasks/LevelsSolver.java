package algorithms.tasks;

import java.util.Collections;
import java.util.List;

public class LevelsSolver {

    public int solve1(List<Integer> levels) {
        if (levels == null || levels.isEmpty()) {
            return 0;
        }

        Collections.sort(levels, Collections.reverseOrder()); // Sort in descending order
        int steps = 0;
        int currentMax = levels.get(0);

        for (int i = 1; i < levels.size(); i++) {
            if (levels.get(i) < currentMax) {
                steps += i; // Number of elements to reduce to currentMax
                currentMax = levels.get(i); // Update currentMax
            }
        }
        return steps;
    }


    public int solve(List<Integer> levels) {
        if (levels.size() <= 1) {
            return 0;
        }
        levels.sort(Collections.reverseOrder());
        if (levels.get(0).equals(levels.get(levels.size() - 1))) {
            return 0;
        }
        int result = 0;
        int max = levels.get(0);
        for (int i = 1; i < levels.size(); i++) {
            if (levels.get(i) < max) {
                max = levels.get(i);
                result = result + i;
            }
        }
        return result;
    }
}
