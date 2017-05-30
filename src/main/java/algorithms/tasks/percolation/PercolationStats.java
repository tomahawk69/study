package algorithms.tasks.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private final double[] thresholds;
    private double confidenceLo1;
    private double confidenceHi1;
    private double mean1;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
        }
        this.n = n;
        this.trials = trials;
        this.thresholds = new double[trials];
        this.calculateTrials();
    }

    private void calculateTrials() {
        for (int i = 0; i < thresholds.length; i++) {
            thresholds[i] = calculateThreshold();
        }
        mean1 = StdStats.mean(thresholds);
        confidenceLo1 = mean() - ((1.96 * stddev()) / Math.sqrt(trials));
        confidenceHi1 = mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    private double calculateThreshold() {
        Percolation percolation = new Percolation(n);
        int result = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, n + 1);
            int j = StdRandom.uniform(1, n + 1);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                result++;
            }
        }
        return result * 1.0/(n * n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean1;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi1;
    }

    // test client (described below)
    public static void main(String[] args) {
//        StdRandom.setSeed(100);
        if (args.length != 2) {
            StdOut.println("Expected 2 arguments: dimension n and trials count");
        } else {
            PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            StdOut.println("mean\t\t\t\t\t= " + stats.mean());
            StdOut.println("stddev\t\t\t\t\t= " + stats.stddev());
            StdOut.println("95% confidence interval\t= " + stats.confidenceLo() + ", " + stats.confidenceHi());
        }
    }
}