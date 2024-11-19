package algorithms.tasks;

import java.util.Arrays;
import java.util.List;

public class Candies {

    public long candies(List<Integer> children) {
        int[] dp = new int[children.size()];
        Arrays.fill(dp, 1);
        for (int i = 1; i < children.size(); i++) {
            populateLeft(children, dp, i);
        }
        for (int i = children.size() - 2; i >= 0; i--) {
            populateRight(children, dp, i);
        }
        long result = 0;
        for (int i : dp) {
            result += i;
        }
        return result;
    }

    private static void populateLeft(List<Integer> children, int[] dp, int i) {
        int current = children.get(i);
        int prev = children.get(i - 1);
        if (current > prev) {
            dp[i] = dp[i - 1] + 1;
        }
    }

    private static void populateRight(List<Integer> children, int[] dp, int i) {
        int current = children.get(i);
        int prev = children.get(i + 1);
        if (current > prev) {
            int right = dp[i + 1] + 1;
            dp[i] = Math.max(dp[i], right);
        }
    }
}
