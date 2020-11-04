package algorithms.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PowerSum {

    static class Chain {
        int sum;
        List<Integer> chain;
    }

    static int powerSum(int X, int N) {
        int[] squares = new int[X];
        List<List<Integer>> result = new LinkedList<>();
        int size = prepareSquares(squares, N, X);
        Chain chain = new Chain();
        chain.chain = new ArrayList<>();
        int[] squaresReduced = Arrays.copyOfRange(squares, 0, size);
        for (int i = 0; i < size; i++) {
            recursive(i, chain, squaresReduced, X, result);
        }
        return result.size();
    }

    private static int prepareSquares(int[] squares, int power, int goal) {
        for (int i = 0; i < squares.length; i++) {
            int j = power(i + 1, power);
            if (j <= goal) {
                squares[i] = j;
            } else {
                return i;
            }
        }
        return squares.length;
    }

    private static void recursive(int i, Chain chain, int[] squares, int goal, List<List<Integer>> result) {
        int newSum = chain.sum + squares[i];
        if (newSum == goal) {
            List<Integer> done = new ArrayList<>(chain.chain);
            done.add(squares[i]);
            result.add(done);
        } else if (newSum <= goal) {
            Chain next = new Chain();
            next.chain = new ArrayList<>(chain.chain);
            next.sum = newSum;
            next.chain.add(squares[i]);
            for (int j = i + 1; j < squares.length; j++) {
                recursive(j, next, squares, goal, result);
            }
        }
    }

    private static int power(int i, int power) {
        int result = 1;
        for (int j = 0; j < power; j++) {
            result *= i;
        }
        return result;
    }

}
