package algorithms.tasks;

import java.util.List;

public class ChangeCoins {

    public int change(int n, List<Integer> coins) {
        Integer[] sorted = coins.stream()
                .filter(i -> i <= n)
                .sorted()
                .toArray(Integer[]::new);
        return change(n, sorted);
    }

    private int change(int n, Integer[] coins) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= n; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[n];
    }
}
