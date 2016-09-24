package algorithms.tasks.hedgehog;

/**
 * Animal interface is used for determine movies through garden
 * And (possibly) get startPoint
 */
public interface Animal {
    Point[] getNextMovies(Point currentPoint);
    Point getPreferredStartPoint();
}
