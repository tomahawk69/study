package algorithms.tasks.patternrecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int size;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        checkForDuplicates(points);
        Map<Double, List<Point[]>> mapOfSlopes = new HashMap<>();
        for (int i = 0; i < points.length - 3; i++) {
            Point point = points[i];
            Map<Double, List<Point>> mapOfListOfPoints = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                double slope = point.slopeTo(points[j]);
                List<Point> list = mapOfListOfPoints.get(slope);
                if (list == null) {
                    list = new LinkedList<>();
                    mapOfListOfPoints.put(slope, list);
                }
                list.add(points[j]);
            }
            mapOfListOfPoints.entrySet().stream()
                    .filter(e -> e.getValue().size() > 2)
                    .forEach(e -> addSegment(point, e.getValue(), e.getKey(), mapOfSlopes));
        }
        populateSegments(mapOfSlopes);
    }

    private void populateSegments(Map<Double, List<Point[]>> mapOfSlopes) {
        segments = mapOfSlopes.values().stream()
                .flatMap(l -> l.stream().map(this::populateSegment))
                .toArray(LineSegment[]::new);
    }

    private LineSegment populateSegment(Point[] points) {
        Point min = points[0];
        Point max = min;
        for (int j = 1; j < points.length; j++) {
            if (points[j].compareTo(min) < 0) {
                min = points[j];
            } else if (points[j].compareTo(max) > 0) {
                max = points[j];
            }
        }
        return new LineSegment(min, max);
    }

    private void addSegment(Point basePoint, List<Point> points, Double slope, Map<Double, List<Point[]>> slopes) {
        Point secondPoint = points.get(0);
        List<Point[]> listOfSegmentArrays = slopes.get(slope);
        if (listOfSegmentArrays == null) {
            listOfSegmentArrays = new LinkedList<>();
            slopes.put(slope, listOfSegmentArrays);
        } else {
            for (Point[] p : listOfSegmentArrays) {
                for (int j = 0, count = 0; j < p.length; j++) {
                    if (p[j] == basePoint || p[j] == secondPoint) {
                        if (count++ > 0) {
                            return;
                        }
                    }
                }
            }
        }
        Point[] newPoints = new Point[points.size() + 1];
        newPoints[0] = basePoint;
        int i = 1;
        for (Point p : points) {
            newPoints[i++] = p;
        }
        size++;
        listOfSegmentArrays.add(newPoints);
    }

    private void checkForDuplicates(Point[] points) {


        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
