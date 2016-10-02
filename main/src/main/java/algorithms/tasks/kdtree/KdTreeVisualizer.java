package algorithms.tasks.kdtree;

import edu.princeton.cs.algs4.*;

public class KdTreeVisualizer {

    private static void demo() {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        for (int i = 0; i < 3; i++) {
            double x = StdRandom.uniform(100) / 100.0;
            double y = StdRandom.uniform(100) / 100.0;
            StdOut.printf("%8.6f %8.6f\n", x, y);
            Point2D p = new Point2D(x, y);
            if (rect.contains(p)) {
                StdOut.printf("%8.6f %8.6f\n", x, y);
                kdtree.insert(p);
                StdDraw.clear();
                kdtree.draw();
                StdDraw.show();
            }
            StdDraw.pause(50);
        }
    }

    public static void demo2() {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.206107, 0.095492));
        kdtree.insert(new Point2D(0.975528, 0.654508));
        kdtree.insert(new Point2D(0.024472, 0.345492));
        kdtree.insert(new Point2D(0.793893, 0.095492));

        kdtree.insert(new Point2D(0.793893, 0.904508));
        kdtree.insert(new Point2D(0.975528, 0.345492));

        kdtree.insert(new Point2D(0.206107, 0.904508));

        kdtree.insert(new Point2D(0.500000, 0.000000));
        kdtree.insert(new Point2D(0.024472, 0.654508));
        kdtree.insert(new Point2D(0.500000, 1.000000));
        StdDraw.clear();
        kdtree.draw();
        StdDraw.show();
    }

    public static void main(String[] args) {
        demo3();
    }

    private static void demo3() {

        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        while (true) {
            if (StdDraw.mousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    kdtree.insert(p);
                    StdDraw.clear();
                    kdtree.draw();
                    StdDraw.show();
                }
            }
            StdDraw.pause(50);
        }

    }

}
