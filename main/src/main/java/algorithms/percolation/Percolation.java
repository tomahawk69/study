package algorithms.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 */
public class Percolation {
    private final boolean[][] data;
    private final int size;
    private final WeightedQuickUnionUF wqf;
    private final WeightedQuickUnionUF fullWqf;

    // create size-by-size grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        data = new boolean[size][size];
        wqf = new WeightedQuickUnionUF(size * size + 2);
        fullWqf = new WeightedQuickUnionUF(size * size + 1);
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (i == 1) {
            wqf.union(getQFIndex(i, j), 0);
            fullWqf.union(getQFIndex(i, j), 0);
        }
        if (i == size) {
            wqf.union(getQFIndex(i, j), size * size + 1);
            // the only difference from percolation data
        }


        if (j > 1 && isOpen(i, j - 1)) {
            wqf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
            fullWqf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            wqf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
            fullWqf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            wqf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
            fullWqf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            wqf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
            fullWqf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
        data[i - 1][j - 1] = true;
    }

    private int getQFIndex(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException();
        }
        return (i - 1) * size + j;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return data[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return isOpen(i, j) && fullWqf.connected(0, getQFIndex(i, j));
    }

    // does the system percolate?
    public boolean percolates() {
        return wqf.connected(0, size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
