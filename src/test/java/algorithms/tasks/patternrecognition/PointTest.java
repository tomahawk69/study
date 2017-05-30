package algorithms.tasks.patternrecognition;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void compareTo() throws Exception {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(10, 11);
        Point point3 = new Point(11, 10);
        Point point4 = new Point(10, 10);
        assertTrue(point1.compareTo(point2) < 0);
        assertTrue(point2.compareTo(point1) > 0);
        assertTrue(point1.compareTo(point3) < 0);
        assertTrue(point3.compareTo(point1) > 0);
        assertTrue(point4.compareTo(point1) == 0);
        assertTrue(point1.compareTo(point4) == 0);
    }

    @Test
    public void slopeTo() throws Exception {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(10, 10);
        Point point3 = new Point(10, 15);
        Point point4 = new Point(14, 10);
        Point point5 = new Point(14, 16);
        assertEquals(Double.NEGATIVE_INFINITY, point1.slopeTo(point2), 0.0);
        assertEquals(Double.NEGATIVE_INFINITY, point2.slopeTo(point1), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, point3.slopeTo(point1), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, point1.slopeTo(point3), 0.0);
        assertEquals(+0.0, point1.slopeTo(point4), 0.0);
        assertEquals(+0.0, point4.slopeTo(point1), 0.0);
        assertTrue(point1.slopeTo(point5) > 0);
        assertTrue(point5.slopeTo(point1) > 0);
        assertEquals(point5.slopeTo(point1), point1.slopeTo(point5), 0.0);
    }

    @Test
    public void testComparator() throws Exception {
        Point point1 = new Point(10, 10);
        Point point2 = new Point(10, 10);
        Point point3 = new Point(10, 15);
        Comparator<Point> comparator = point1.slopeOrder();
        assertEquals(-1, comparator.compare(point2, point3));
        assertEquals(1, comparator.compare(point3, point2));
        assertEquals(0, comparator.compare(point2, point2));
        assertEquals(0, comparator.compare(point3, point3));
    }
}