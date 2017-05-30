package algorithms.tasks.patternrecognition;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FastCollinearPointsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPositive() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(10, 11);
        Point[] points = {p1, p3, p5, p4, p2};
        FastCollinearPoints service = new FastCollinearPoints(points);
        assertEquals(1, service.numberOfSegments());
        assertEquals(new LineSegment(p1, p5).toString(), service.segments()[0].toString());
    }

    @Test
    public void testNegative() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 4);
        Point[] points = {p1, p3, p4, p2};
        FastCollinearPoints service = new FastCollinearPoints(points);
        assertEquals(0, service.numberOfSegments());
    }

    @Ignore
    @Test
    public void testCollateralSorting() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(0, 2);
        Point p5 = new Point(6, 1);
        Point p6 = new Point(2, 2);

        Point[] points = {p1, p2, p3, p4, p5, p6};
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, p1.slopeOrder());
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, p2.slopeOrder());
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, p3.slopeOrder());
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, p4.slopeOrder());
        System.out.println(Arrays.toString(points));
    }

    @Test
    public void testArraysNull() throws Exception {
        expectedException.expect(NullPointerException.class);
        new FastCollinearPoints(null);
    }

    @Test
    public void testPointNull() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point[] points = {p1, p3, p4, p2, null};
        expectedException.expect(NullPointerException.class);
        new FastCollinearPoints(points);
    }

    @Test
    public void testDuplicates() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 5);
        Point[] points = {p1, p3, p4, p2, p1};
        expectedException.expect(IllegalArgumentException.class);
        new FastCollinearPoints(points);
    }

    @Test
    public void testBigGrid() throws Exception {
        int sizeX = 4;
        int sizeY = 2048;
        Point[] points = new Point[sizeX * sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                points[i * sizeY + j] = new Point(i, j);
            }
        }
        long startTime = System.currentTimeMillis();
        FastCollinearPoints service = new FastCollinearPoints(points);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)/1000.0);
        assertEquals(1398106, service.numberOfSegments());
    }

}