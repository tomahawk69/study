package algorithms.tasks;

import java.math.BigDecimal;

public class CountArrays {
    private static final int MOD = 1_000_000_007;

    public long countArray(int n, int k, int x) {
        long dp1 = 0;
        long dpk = 0;
        long dp1Prev = 1;
        for (int i = 2; i < n + 1; ++i) {
            dp1 = (dpk * (k - 1)) % MOD;
            dpk = (dp1Prev + dpk * (k - 2)) % MOD;
            dp1Prev = dp1;
        }
        if (x == 1) {
            return dp1;
        } else {
            return dpk;
        }
    }

    public long countArrayNaive(int n, int k, int x) {
        long[] prev = new long[k + 1];
        prev[1] = 1;
        long totalSum = 1;

        for (int i = 2; i <= n; i++) {
            long[] curr = new long[k + 1];

            long newTotalSum = 0;

            for (int j = 1; j <= k; j++) {
                curr[j] = (totalSum - prev[j] + MOD) % MOD;
                newTotalSum = (newTotalSum + curr[j]) % MOD;
            }

            prev = curr;
            totalSum = newTotalSum;
        }

        return prev[x];
    }

    public long countArrayBigDecimal(int n, int k, int x) {
        BigDecimal m = BigDecimal.valueOf((long) Math.pow(10, 9) + 7);
        BigDecimal[] prev = new BigDecimal[k + 1];
        prev[1] = BigDecimal.ONE;
        BigDecimal totalSum = BigDecimal.ONE;
        for (int i = 2; i < n + 1; i++) {
            BigDecimal[] curr = new BigDecimal[k + 1];
            BigDecimal nextSum = BigDecimal.ZERO;
            for (int j = 1; j < k + 1; j++) {
                if (prev[j] == null) {
                    curr[j] = totalSum;
                } else {
                    curr[j] = totalSum.add(prev[j].negate());
                }
                nextSum = nextSum.add(curr[j]);
            }
            prev = curr;
            totalSum = nextSum;
        }
        return prev[x].remainder(m).longValue();
    }
}
