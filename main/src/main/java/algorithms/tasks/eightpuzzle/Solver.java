package algorithms.tasks.eightpuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

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
