package algorithms.tasks.hedgehog;

import java.util.Arrays;

public class SolutionBruteForce implements Solution {

    @Override
    public int getMaxApples(Garden garden, Animal animal) {
        Point startPoint = animal.getPreferredStartPoint();
        if (startPoint == null) {
            startPoint = new Point(0, 0);
        }
        int applesCount = 0;
        if (garden.isBound(startPoint)) {
            applesCount = garden.getApplesCount(startPoint);
        }
        return iterateGarden(garden, animal, startPoint, applesCount);
    }

    public int iterateGarden(Garden garden, Animal animal, Point point, int applesCount) {
        return Math.max(applesCount,
                Arrays.stream(animal.getNextMovies(point))
                .filter(p -> garden.isBound(p))
                .map(p -> iterateGarden(garden, animal, p, applesCount + garden.getApplesCount(p)))
                .max(Integer::compareTo)
                .orElse(0));
    }
}
