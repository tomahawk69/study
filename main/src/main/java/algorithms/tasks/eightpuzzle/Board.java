package algorithms.tasks.eightpuzzle;

import java.util.ArrayList;
import java.util.List;

public class Board {
    //    MinPQ<>
    private final int boardSize;
    private final int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        boardSize = blocks.length;
        this.blocks = cloneBlocks(blocks);
    }

    // board dimension n
    public int dimension() {
        return boardSize;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int value = blocks[i][j];
                if (value != 0 && value != i * boardSize + j + 1) result++;
            }
        }
        return result;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int value = blocks[i][j];
                if (value > 0 && value != i * boardSize + j + 1) {
                    int x = (value - 1) / boardSize;
                    int y = value - x * boardSize - 1;
                    result += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] newBlocks = cloneBlocks(blocks);
        int i = 0, j = 0;
        if (newBlocks[i][j] == 0 || newBlocks[i][j + 1] == 0) {
            i++;
            j++;
        }
        int value = newBlocks[i][j];
        if (i == boardSize - 1) {
            newBlocks[i][j] = newBlocks[i][j - 1];
            newBlocks[i][j - 1] = value;
        } else {
            newBlocks[i][j] = newBlocks[i][j + 1];
            newBlocks[i][j + 1] = value;
        }
        return new Board(newBlocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board other = (Board) y;
        if (boardSize != other.boardSize) return false;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (blocks[i][j] != other.blocks[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int emptyX = 0, emptyY = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (blocks[i][j] == 0) {
                    emptyX = i;
                    emptyY = j;
                    break;
                }
            }
        }
        List<Board> boards = new ArrayList<>();
        if (emptyX > 0) {
            int[][] newBlock = cloneBlocks(blocks);
            newBlock[emptyX - 1][emptyY] = blocks[emptyX][emptyY];
            newBlock[emptyX][emptyY] = blocks[emptyX - 1][emptyY];
            boards.add(new Board(newBlock));
        }
        if (emptyX < boardSize - 1) {
            int[][] newBlock = cloneBlocks(blocks);
            newBlock[emptyX + 1][emptyY] = blocks[emptyX][emptyY];
            newBlock[emptyX][emptyY] = blocks[emptyX + 1][emptyY];
            boards.add(new Board(newBlock));
        }
        if (emptyY > 0) {
            int[][] newBlock = cloneBlocks(blocks);
            newBlock[emptyX][emptyY - 1] = blocks[emptyX][emptyY];
            newBlock[emptyX][emptyY] = blocks[emptyX][emptyY - 1];
            boards.add(new Board(newBlock));
        }
        if (emptyY < boardSize - 1) {
            int[][] newBlock = cloneBlocks(blocks);
            newBlock[emptyX][emptyY + 1] = blocks[emptyX][emptyY];
            newBlock[emptyX][emptyY] = blocks[emptyX][emptyY + 1];
            boards.add(new Board(newBlock));
        }
        return boards;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder result = new StringBuilder().append(boardSize + "\n");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                result.append(String.format("%2d ", blocks[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private static int[][] cloneBlocks(int[][] blocks) {
        int[][] result = new int[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            result[i] = blocks[i].clone();
        }
        return result;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}