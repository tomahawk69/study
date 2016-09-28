package algorithms.tasks.eightpuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

/**
 * Write a program to solve the 8-puzzle problem
 * (and its natural generalizations) using the A* search algorithm.
 * <p>
 * The 8-puzzle problem is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s.
 * It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square.
 * Your goal is to rearrange the blocks so that they are in order, using as few moves as possible.
 * You are permitted to slide blocks horizontally or vertically into the blank square.
 * <p>
 * Best-first search.
 * Now, we describe a solution to the problem that illustrates a general artificial intelligence methodology
 * known as the A* search algorithm. We define a search node of the game to be a board, the number of moves
 * made to reach the board, and the previous search node. First, insert the initial search node
 * (the initial board, 0 moves, and a null previous search node) into a priority queue. Then, delete from
 * the priority queue the search node with the minimum priority, and insert onto the priority queue all
 * neighboring search nodes (those that can be reached in one move from the dequeued search node).
 * Repeat this procedure until the search node dequeued corresponds to a goal board. The success of this
 * approach hinges on the choice of priority function for a search node. We consider two priority functions:
 * - Hamming priority function. The number of blocks in the wrong position, plus the number of moves made so
 * far to get to the search node. Intuitively, a search node with a small number of blocks in the wrong position
 * is close to the goal, and we prefer a search node that have been reached using a small number of moves.
 * - Manhattan priority function. The sum of the Manhattan distances (sum of the vertical and horizontal distance)
 * from the blocks to their goal positions, plus the number of moves made so far to get to the search node.
 * <p>
 * Detecting unsolvable puzzles
 * To detect such situations, use the fact that boards are divided into two equivalence classes with respect
 * to reachability: (i) those that lead to the goal board and (ii) those that lead to the goal board if we modify
 * the initial board by swapping any pair of blocks (the blank square is not a block).
 * To apply the fact, run the A* algorithm on two puzzle instances—one with the initial board and one with
 * the initial board modified by swapping a pair of blocks—in lockstep (alternating back and forth between
 * exploring search nodes in each of the two game trees). Exactly one of the two will lead to the goal board.
 */
public class Solver {
    private MinPQ<Move> queue = new MinPQ<>();
    private MinPQ<Move> queueTwin = new MinPQ<>();
    private Move goal;
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        queue.insert(new Move(initial, null, 0));
        queueTwin.insert(new Move(initial.twin(), null, 0));
        doSolve();
    }

    private void doSolve() {
        while (true) {
            // initial board search
            // no solution
            if (queue.isEmpty()) {
                queue = null;
                queueTwin = null;
                break;
            }
            Move current = queue.delMin();
            if (current.board.isGoal()) {
                goal = current;
                queue = null;
                queueTwin = null;
                break;
            }
            moves++;
            for (Board board : current.board.neighbors()) {
                if (current.previous == null || !board.equals(current.previous.board)) {
                    queue.insert(new Move(board, current, current.step + 1));
                }
            }

            // twin board search
            if (!queueTwin.isEmpty()) {
                current = queueTwin.delMin();
                if (current.board.isGoal()) {
                    break;
                }
                for (Board board : current.board.neighbors()) {
                    if (current.previous == null || !board.equals(current.previous.board)) {
                        queueTwin.insert(new Move(board, current, current.step + 1));
                    }
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return goal != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (goal == null) {
            return -1;
        } else {
            return goal.step;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (goal == null) {
            return null;
        } else {
            List<Board> result = new LinkedList<>();
            Move move = goal;
            while (move != null) {
                result.add(0, move.board);
                move = move.previous;
            }
            return result;
        }
    }

    private static class Move implements Comparable<Move> {
        private final Board board;
        private final Move previous;
        private final int step;
        private int priority = -1;

        private Move(Board board, Move previous, int step) {
            this.board = board;
            this.previous = previous;
            this.step = step;
        }

        public int getPriority() {
            if (priority < 0) {
                priority = this.step + this.board.manhattan();
            }
            return priority;
        }

        @Override
        public int compareTo(Move o) {
            return this.getPriority() - o.getPriority();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Move move = (Move) o;

            if (step != move.step) return false;
            if (priority != move.priority) return false;
            return board.equals(move.board) && (previous != null ? previous.equals(move.previous) : move.previous == null);

        }

        @Override
        public int hashCode() {
            int result = board.hashCode();
            result = 31 * result + (previous != null ? previous.hashCode() : 0);
            result = 31 * result + step;
            result = 31 * result + priority;
            return result;
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
