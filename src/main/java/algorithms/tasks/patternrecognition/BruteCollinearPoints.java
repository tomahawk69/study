package algorithms.tasks.patternrecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkForDuplicates(points);
        List<Point[]> segmentsList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double slope1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; k++) {
                    double slope2 = points[i].slopeTo(points[k]);
                    if (slope1 == slope2) {
                        for (int l = k + 1; l < points.length; l++) {
                            double slope3 = points[i].slopeTo(points[l]);
                            if (slope1 == slope2 && slope2 == slope3) {
                                Point[] result = {points[i], points[j], points[k], points[l]};
                                addSegment(segmentsList, result);
                            }
                        }
                    }
                }
            }
        }
        segments = new LineSegment[segmentsList.size()];
        for (int i = 0; i < segments.length; i++) {
            Point[] p = segmentsList.get(i);
            segments[i] = new LineSegment(p[0], p[p.length - 1]);
        }
    }

    private void addSegment(List<Point[]> segmentsList, Point[] points) {
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.compareTo(o2);
            }
        });
        for (Point[] p : segmentsList) {
            int count = 0;
            for (Point p1 : p) {
                if (p1 == points[0] || p1 == points[1] || p1 == points[2] || p1 == points[3]) {
                    count++;
                    if (count > 1) {
                        return;
                    }
                }
            }
        }
        segmentsList.add(points);

    }

    private void checkForDuplicates(Point[] points) {
        int result = IntStream.range(0, points.length)
                .flatMap(i -> IntStream.range(i, points.length)
                        .filter(j -> points[i].compareTo(points[j]) == 0))
                .findFirst()
                .orElse(-1);
        if (result >= 0) {
            throw new IllegalArgumentException();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
