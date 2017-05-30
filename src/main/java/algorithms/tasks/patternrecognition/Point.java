package algorithms.tasks.patternrecognition;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Create an immutable data type Point that represents a point in the plane
 * <p>
 * Your job is to add the following components.
 * - The compareTo() method should compare points by their y-coordinates, breaking ties by their x-coordinates.
 * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if
 * either y0 < y1 or if y0 = y1 and x0 < x1.
 * - The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1),
 * which is given by the formula (y1 − y0) / (x1 − x0).
 * Treat the slope of a horizontal line segment as positive zero;
 * treat the slope of a vertical line segment as positive infinity;
 * treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
 * - The slopeOrder() method should return a comparator that compares its two argument points by the slopes
 * they make with the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2)
 * if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
 * Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
 * <p>
 * Corner cases.
 * To avoid potential complications with integer overflow or floating-point precision, you may assume
 * that the constructor arguments x and y are each between 0 and 32,767.
 */
public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        int compareY = y - that.y;
        if (compareY == 0) {
            return x - that.x;
        } else {
            return compareY;
        }
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that.x == x) {
            if (that.y == y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else {
            if (that.y == y) {
                return +0.0;
            } else {
                return (that.y - y) / (0.0 + that.x - x);
            }
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(Point.this.slopeTo(o1), Point.this.slopeTo(o2));
        }
    }

}