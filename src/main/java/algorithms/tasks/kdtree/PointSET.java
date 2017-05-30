package algorithms.tasks.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Write a mutable data type PointSET.java that represents a set of points in the unit square
 * using either SET from algs4.jar or java.util.TreeSet
 * Use Point2D and RectHV
 */
public class PointSET {
    private final Set<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        this.points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.size() == 0;
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : points) {
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {
                Set<Point2D> result = new TreeSet<>();
                for (Point2D point : points) {
                    if (rect.contains(point)) {
                        result.add(point);
                    }
                }
                return result.iterator();
            }
        };
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }
        double distanceMax = Double.MAX_VALUE;
        Point2D result = null;
        for (Point2D point : points) {
            double distance = p.distanceSquaredTo(point);
            if (distance < distanceMax) {
                distanceMax = distance;
                result = point;
            }
        }
        return result;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {


    }
}
