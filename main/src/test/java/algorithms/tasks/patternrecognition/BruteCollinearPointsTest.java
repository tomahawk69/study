package algorithms.tasks.patternrecognition;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BruteCollinearPointsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPositive() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point[] points = {p1, p3, p4, p2};
        BruteCollinearPoints service = new BruteCollinearPoints(points);
        assertEquals(1, service.numberOfSegments());
        assertEquals(new LineSegment(p1, p4).toString(), service.segments()[0].toString());
    }

    @Test
    public void testArraysNull() throws Exception {
        expectedException.expect(NullPointerException.class);
        BruteCollinearPoints service = new BruteCollinearPoints(null);
    }

    @Test
    public void testPointNull() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point[] points = {p1, p3, p4, p2, null};
        expectedException.expect(NullPointerException.class);
        new BruteCollinearPoints(points);
    }

    @Test
    public void testDuplicates() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point[] points = {p1, p3, p4, p2, p1};
        expectedException.expect(IllegalArgumentException.class);
        new BruteCollinearPoints(points);
    }
}