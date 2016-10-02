package algorithms.tasks.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.LinkedList;

public class KdTree {
    private static final double PEN_RADIUS = 0.01;
    private static final Color PEN_COLOR = StdDraw.BLACK;
    private static final Color LINE_HORIZONTAL = StdDraw.BLUE;
    private static final Color LINE_VERTICAL = StdDraw.RED;
    private static final RectHV EMPTY_TREE_RECT = new RectHV(0, 0, 1, 1);

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        root = insert(root, true, p);
    }

    private Node insert(Node node, boolean isOdd, Point2D p) {
        if (node == null) {
            size++;
            return new Node(p, isOdd);
        }
        int cmp = compareByAxis(node, p);
        if (cmp == 0 && compareByAxisReverted(node, p) == 0) {
            return node;
        }

        if (cmp < 0) node.left = insert(node.left, !node.isOdd, p);
        else node.right = insert(node.right, !node.isOdd, p);

        return node;
    }

    private int compareByAxisReverted(Node node, Point2D point) {
        return node.isOdd ? Point2D.Y_ORDER.compare(point, node.point) : Point2D.X_ORDER.compare(point, node.point);
    }

    private int compareByAxis(Node node, Point2D point) {
        return node.isOdd ? Point2D.X_ORDER.compare(point, node.point) : Point2D.Y_ORDER.compare(point, node.point);
    }

    // does the set contain point point?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null) {
            return false;
        }
        int cmp = compareByAxis(node, p);
        if (cmp == 0 && compareByAxisReverted(node, p) == 0) {
            return true;
        }
        if (cmp < 0) {
            return contains(node.left, p);
        } else {
            return contains(node.right, p);
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, EMPTY_TREE_RECT);
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) return;

        // draw a line
        StdDraw.setPenColor(PEN_COLOR);
        StdDraw.setPenRadius(PEN_RADIUS);
        StdDraw.point(node.point.x(), node.point.y());
        StdDraw.setPenRadius();

        if (node.isOdd) {
            StdDraw.setPenColor(LINE_VERTICAL);
            StdDraw.line(node.point.x(), rect.ymin(), node.point.x(), rect.ymax());
        } else {
            StdDraw.setPenColor(LINE_HORIZONTAL);
            StdDraw.line(rect.xmin(), node.point.y(), rect.xmax(), node.point.y());
        }
        draw(node.left, createLeftRectangle(node.isOdd, rect, node.point));
        draw(node.right, createRightRectangle(node.isOdd, rect, node.point));
    }

    private RectHV createRightRectangle(boolean isOdd, RectHV rect, Point2D point) {
        if (isOdd) {
            return new RectHV(point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), point.y(), rect.xmax(), rect.ymax());
        }
    }

    private RectHV createLeftRectangle(boolean isOdd, RectHV rect, Point2D point) {
        if (isOdd) {
            return new RectHV(rect.xmin(), rect.ymin(), point.x(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        LinkedList<Point2D> result = new LinkedList<>();
        range(root, result, EMPTY_TREE_RECT, rect);
        return result;
    }

    private void range(Node node, LinkedList<Point2D> result, RectHV rect, RectHV queryRect) {
        if (node == null) {
            return;
        }
        if (queryRect.contains(node.point)) {
            result.add(node.point);
        }
        if (node.left != null) {
            RectHV rectLeft = createLeftRectangle(node.isOdd, rect, node.point);
            if (queryRect.intersects(rectLeft)) {
                range(node.left, result, rectLeft, queryRect);
            }
        }
        if (node.right != null) {
            RectHV rectRight = createRightRectangle(node.isOdd, rect, node.point);
            if (queryRect.intersects(rectRight)) {
                range(node.right, result, rectRight, queryRect);
            }
        }
    }

    // a nearest neighbor in the set to point point; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }

        double distance = root.point.distanceSquaredTo(p);
        return nearest(root, EMPTY_TREE_RECT, p, null, Double.MAX_VALUE, distance);

    }

    private Point2D nearest(Node node, RectHV rect, Point2D p, Point2D nearestPoint,
                            double distanceMin, double distance) {
        if (node == null) {
            return nearestPoint;
        }
        if (distance > distanceMin && rect.distanceSquaredTo(p) > distanceMin) {
            return nearestPoint;
        } else if (distance < distanceMin) {
            distanceMin = distance;
            nearestPoint = node.point;
        }
        boolean firstSideLeft = node.left != null &&
                (node.right == null ||
                        node.isOdd && node.point.x() >= p.x() ||
                        !node.isOdd && node.point.y() >= p.y());

        if (firstSideLeft) {
            Point2D oldNearestPoint = nearestPoint;
            nearestPoint = nearest(node.left, createLeftRectangle(node.isOdd, rect, node.point), p,
                    nearestPoint, distanceMin, node.left.point.distanceSquaredTo(p));
            if (node.right != null) {
                if (oldNearestPoint != nearestPoint) {
                    distanceMin = p.distanceSquaredTo(nearestPoint);
                }
                nearestPoint = nearest(node.right, createRightRectangle(node.isOdd, rect, node.point), p,
                        nearestPoint, distanceMin, node.right.point.distanceSquaredTo(p));
            }
        } else if (node.right != null) {
            Point2D oldNearestPoint = nearestPoint;
            nearestPoint = nearest(node.right, createRightRectangle(node.isOdd, rect, node.point), p,
                    nearestPoint, distanceMin, node.right.point.distanceSquaredTo(p));
            if (node.left != null) {
                if (oldNearestPoint != nearestPoint) {
                    distanceMin = p.distanceSquaredTo(nearestPoint);
                }
                nearestPoint = nearest(node.left, createLeftRectangle(node.isOdd, rect, node.point), p,
                        nearestPoint, distanceMin, node.left.point.distanceSquaredTo(p));
            }
        }
        return nearestPoint;
    }

    private static class Node {
        private final Point2D point;      // the point
        private final boolean isOdd;
        private Node left;        // the left/bottom subtree
        private Node right;        // the right/top subtree

        private Node(Point2D point, boolean isOdd) {
            this.point = point;
            this.isOdd = isOdd;
        }

    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
