package algorithms.tasks.hedgehog;

public class Hedgehog implements Animal {
    private static final Point START_POINT = new Point(0, 0);

    @Override
    public Point[] getNextMovies(Point currentPoint) {
        Point[] result = {
                new Point(currentPoint.getX(), currentPoint.getY() + 1),
                new Point(currentPoint.getX() + 1, currentPoint.getY()),
        };
        return result;
    }

    @Override
    public Point getPreferredStartPoint() {
        return START_POINT;
    }


}
