package algorithms.tasks.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;

import static org.junit.Assert.*;

public class PointSETTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEmptyPointSet() throws Exception {
        PointSET pointSET = new PointSET();
        assertTrue(pointSET.isEmpty());
        assertEquals(0, pointSET.size());
    }

    @Test
    public void testNotEmptyPointSet() throws Exception {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(1, 1));
        assertFalse(pointSET.isEmpty());
        assertEquals(1, pointSET.size());
    }

    @Test
    public void testInsertNull() throws Exception {
        PointSET pointSET = new PointSET();
        expectedException.expect(NullPointerException.class);
        pointSET.insert(null);
    }

    @Test
    public void testNearestPoint1Element() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point = new Point2D(1, 1);
        Point2D point2Search = new Point2D(3, 5);
        pointSET.insert(point);
        Point2D result = pointSET.nearest(point2Search);
        assertNotNull(result);
        assertEquals(point, result);
    }

    @Test
    public void testNearestPointEmptySet() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point2Search = new Point2D(3, 5);
        Point2D result = pointSET.nearest(point2Search);
        assertNull(result);
    }

    @Test
    public void testNearestPoint2Elements() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point1 = new Point2D(1, 1);
        Point2D point2 = new Point2D(2, 2);
        Point2D pointToSearch = new Point2D(3, 5);
        pointSET.insert(point1);
        pointSET.insert(point2);
        Point2D result = pointSET.nearest(pointToSearch);
        assertNotNull(result);
        assertEquals(point2, result);
    }

    @Test
    public void testNearestNull() throws Exception {
        PointSET pointSET = new PointSET();
        expectedException.expect(NullPointerException.class);
        pointSET.nearest(null);
    }

    @Test
    public void testContainPositive() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point = new Point2D(1, 1);
        Point2D pointToSearch = new Point2D(1, 1);
        pointSET.insert(point);
        assertTrue(pointSET.contains(pointToSearch));
    }

    @Test
    public void testContainNegative() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point = new Point2D(2, 2);
        Point2D pointToSearch = new Point2D(1, 1);
        pointSET.insert(point);
        assertFalse(pointSET.contains(pointToSearch));
    }

    @Test
    public void testContainInEmptySet() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D pointToSearch = new Point2D(1, 1);
        assertFalse(pointSET.contains(pointToSearch));
    }

    @Test
    public void testContainNull() throws Exception {
        PointSET pointSET = new PointSET();
        expectedException.expect(NullPointerException.class);
        pointSET.contains(null);
    }

    @Test
    public void testRangeNull() throws Exception {
        PointSET pointSET = new PointSET();
        expectedException.expect(NullPointerException.class);
        pointSET.range(null);
    }

    @Test
    public void testRangePositive() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point1 = new Point2D(1, 1);
        Point2D point2 = new Point2D(3, 3);
        RectHV rectHV = new RectHV(2, 2, 3, 3);
        pointSET.insert(point1);
        pointSET.insert(point2);
        Iterable<Point2D> result = pointSET.range(rectHV);
        assertNotNull(result);
        Iterator<Point2D> iterator = result.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(point2, iterator.next());
        assertFalse(iterator.hasNext());
    }



    @Test
    public void testRangeNegative() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point1 = new Point2D(2, 2);
        RectHV rectHV = new RectHV(0, 0, 1, 1);
        pointSET.insert(point1);
        Iterable<Point2D> result = pointSET.range(rectHV);
        assertNotNull(result);
        Iterator<Point2D> iterator = result.iterator();
        assertFalse(iterator.hasNext());
    }

}