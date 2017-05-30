package algorithms.tasks.hedgehog;

import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

/**
 * The problem is obviously graph problem. Graph type is directed acyclic graph
 * To solve it we may Dijkstra algorithm and inverted graph are used
 * <p>
 * Solution cost is O(m * n)
 */
public class SolutionDijkstra implements Solution {
    private static final int EMPTY_VALUE = 1;


    @Override
    public int getMaxApples(Garden garden, Animal animal) {
        Deque<Point> deque = new LinkedBlockingDeque<>();
        // Integer is not very cheap here however it used for .. Java 8 API instead of two for-cycles
        Integer[][] matrix = IntStream.range(0, garden.getMaxX())
                .mapToObj(l -> IntStream.range(0, garden.getMaxY())
                        .mapToObj(y -> EMPTY_VALUE)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        Point startPoint = animal.getPreferredStartPoint();
        if (startPoint == null) {
            startPoint = new Point(0, 0);
        }

        int result = 0;
        if (matrix.length > 0 ) {
            matrix[startPoint.getX()][startPoint.getY()] = -garden.getApplesCount(startPoint);
            deque.offer(startPoint);
            iterateDeque(garden, animal, matrix, deque);

            result = -matrix[garden.getMaxX() - 1][garden.getMaxY() - 1];
            if (result == EMPTY_VALUE) {
                result = 0;
            }
        }

        return result;
    }

    private void iterateDeque(Garden garden, Animal animal, Integer[][] matrix, Deque<Point> deque) {
        while (!deque.isEmpty()) {
            processField(garden, animal, matrix, deque, deque.poll());
        }
    }

    void processField(final Garden garden, final Animal animal, final Integer[][] matrix, Deque<Point> deque, final Point point) {
        Arrays.stream(animal.getNextMovies(point))
                .filter(p -> garden.isBound(p))
                .forEach(p -> {
                    int value = matrix[point.getX()][point.getY()] - garden.getApplesCount(p);
                    if (matrix[p.getX()][p.getY()] == EMPTY_VALUE) {
                        deque.offer(p);
                    }
                    if (value < matrix[p.getX()][p.getY()]) {
                        matrix[p.getX()][p.getY()] = value;
                    }
                });
    }
}
