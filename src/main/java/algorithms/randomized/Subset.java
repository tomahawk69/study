package algorithms.randomized;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Write a client program Subset.java that takes a command-line integer k; reads in a sequence of N strings
 * from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once. You may assume that 0 ≤ k ≤ n,
 * where N is the number of string on standard input.
 * <p>
 * The running time of Subset must be linear in the size of the input.
 * You may use only a constant amount of memory plus either one Deque or RandomizedQueue object
 * of maximum size at most n, where n is the number of strings on standard input.
 * (For an extra challenge, use only one Deque or RandomizedQueue object of maximum size at most k.)
 * </p>
 * Reservoir sampling is used for minimization of memory usage:
 * - use RandomizedQueue
 * - add all items below k to queue
 * - for every i item greater or equal to k such as Random(0..i) is lesser than k make dequeue and enqueue(item)
 * <p>
 * To run:
 * 1) compile sources
 * javac -classpath E:\works\java\study\libs\algs4.jar |
 * algorithms/randomized/Subset.java algorithms/randomized/RandomizedQueue.java
 * 2) run
 * echo aa bb cc dd | java -cp .;../../../../libs/algs4.jar algorithms.randomized.Subset 3
 * type test.txt | java -cp .;../../../../libs/algs4.jar algorithms.randomized.Subset 3
 */
public class Subset {

    private static void readAndWriteStrings(int k) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        String input;
        int i = 0;
        int chance;
        while (!StdIn.isEmpty()) {
            input = StdIn.readString();
            if (i++ < k) {
                queue.enqueue(input);
            } else {
                chance = StdRandom.uniform(i);
                if (chance < k) {
                    queue.dequeue();
                    queue.enqueue(input);
                }
            }
        }
        while (k-- > 0) {
            StdOut.println(queue.dequeue());
        }

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            StdOut.println("Expected exactly 1 integer argument");
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            throw new IllegalArgumentException();
        }
        readAndWriteStrings(k);

    }
}