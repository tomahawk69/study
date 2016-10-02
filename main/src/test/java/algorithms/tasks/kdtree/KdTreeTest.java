package algorithms.tasks.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class KdTreeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEmptyPointSet() throws Exception {
        KdTree kdTree = new KdTree();
        assertTrue(kdTree.isEmpty());
        assertEquals(0, kdTree.size());
    }

    @Test
    public void testNotEmptyPointSet() throws Exception {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.1, 0.1));
        assertFalse(kdTree.isEmpty());
        assertEquals(1, kdTree.size());
    }

    @Test
    public void testInsertNull() throws Exception {
        KdTree kdTree = new KdTree();
        expectedException.expect(NullPointerException.class);
        kdTree.insert(null);
    }

    @Test
    public void testNearestPoint1Element() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.1, 0.1);
        Point2D point2Search = new Point2D(0.3, 0.5);
        kdTree.insert(point);
        Point2D result = kdTree.nearest(point2Search);
        assertNotNull(result);
        assertEquals(point, result);
    }

    @Test
    public void testNearestPointEmptySet() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point2Search = new Point2D(0.3, 0.5);
        Point2D result = kdTree.nearest(point2Search);
        assertNull(result);
    }

    @Test
    public void testNearestPoint2Elements() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point1 = new Point2D(0.1, 0.1);
        Point2D point2 = new Point2D(0.2, 0.2);
        Point2D pointToSearch = new Point2D(0.3, 0.5);
        kdTree.insert(point1);
        kdTree.insert(point2);
        Point2D result = kdTree.nearest(pointToSearch);
        assertNotNull(result);
        assertEquals(point2, result);
    }

    @Test
    public void testNearestPoint3Elements() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point1 = new Point2D(0.3, 0.3);
        Point2D point2 = new Point2D(0.8, 0.8);
        Point2D pointToSearch = new Point2D(0.5, 0.5);
        kdTree.insert(point1);
        kdTree.insert(point2);
        Point2D result = kdTree.nearest(pointToSearch);
        assertNotNull(result);
        assertEquals(point1, result);
    }

    @Test
    public void testNearestPointFailed() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point1 = new Point2D(0.7303407149712222, 0.42734718641649616);
        kdTree.insert(point1);
        Point2D point2 = new Point2D(0.6294993817708533, 0.026397286504969197);
        kdTree.insert(point2);
        Point2D point3 = new Point2D(0.18901963958098933, 0.04350990880275296);
        kdTree.insert(point3);
        Point2D point4 = new Point2D(0.4347985601251102, 0.6700085398791594);
        kdTree.insert(point4);
        Point2D expectedResult = new Point2D(0.4347985601251102, 0.6700085398791594);
        Point2D pointToSearch = new Point2D(0.3552692866051542, 0.9120799206756177);
        Point2D result = kdTree.nearest(pointToSearch);
        System.out.println(pointToSearch.distanceSquaredTo(point1));
        System.out.println(pointToSearch.distanceSquaredTo(point2));
        System.out.println(pointToSearch.distanceSquaredTo(point3));
        System.out.println(pointToSearch.distanceSquaredTo(point4));
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNearestNull() throws Exception {
        KdTree kdTree = new KdTree();
        expectedException.expect(NullPointerException.class);
        kdTree.nearest(null);
    }

    @Test
    public void testContainPositive() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(StdRandom.uniform(100) / 100.0, StdRandom.uniform(100) / 100.0);
        kdTree.insert(point);
        assertTrue(kdTree.contains(point));
    }

    @Test
    public void testContainNegative() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point = new Point2D(0.2, 0.2);
        Point2D pointToSearch = new Point2D(0.1, 0.1);
        kdTree.insert(point);
        assertFalse(kdTree.contains(pointToSearch));
    }

    @Test
    public void testContainInEmptySet() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D pointToSearch = new Point2D(0.1, 0.1);
        assertFalse(kdTree.contains(pointToSearch));
    }

    @Test
    public void testContainNull() throws Exception {
        KdTree kdTree = new KdTree();
        expectedException.expect(NullPointerException.class);
        kdTree.contains(null);
    }

    @Test
    public void testRangeNull() throws Exception {
        KdTree kdTree = new KdTree();
        expectedException.expect(NullPointerException.class);
        kdTree.range(null);
    }

    @Test
    public void testRangePositive() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point1 = new Point2D(0.1, 0.1);
        Point2D point2 = new Point2D(0.3, 0.3);
        RectHV rectHV = new RectHV(0.2, 0.2, 0.3, 0.3);
        kdTree.insert(point1);
        kdTree.insert(point2);
        Iterable<Point2D> result = kdTree.range(rectHV);
        assertNotNull(result);
        Iterator<Point2D> iterator = result.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(point2, iterator.next());
        assertFalse(iterator.hasNext());
    }


    @Test
    public void testRangeNegative() throws Exception {
        KdTree kdTree = new KdTree();
        Point2D point1 = new Point2D(0.2, 0.2);
        RectHV rectHV = new RectHV(0.0, 0.0, 0.1, 0.1);
        kdTree.insert(point1);
        Iterable<Point2D> result = kdTree.range(rectHV);
        assertNotNull(result);
        Iterator<Point2D> iterator = result.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testBigTreeDiff() throws Exception {
        int j;
        int size = 1000;
        int sampleSize = 1000;
        for (j = 0; j < size; j++) {
            KdTree kdTree = new KdTree();
            PointSET pointSet = new PointSET();

            StdRandom.setSeed(j);
            IntStream.range(0, sampleSize).forEach(i -> {
                        Point2D p = new Point2D(StdRandom.uniform(), StdRandom.uniform());
                        kdTree.insert(p);
                        pointSet.insert(p);
                    }
            );
            Point2D pointToSearch = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            Point2D resultBF = pointSet.nearest(pointToSearch);
            Point2D resultKT = kdTree.nearest(pointToSearch);
            if (resultBF.compareTo(resultKT) != 0) {
                fail("Results are different for seed " + j);
            }

        }
        assertEquals(size, j);
    }

    @Ignore
    @Test
    public void testBigTree() throws Exception {
        KdTree kdTree = new KdTree();
        PointSET pointSet = new PointSET();
        StdRandom.setSeed(11);
        int size = 4;
        IntStream.range(0, size).forEach(i -> {
                    Point2D p = new Point2D(StdRandom.uniform(), StdRandom.uniform());
                    System.out.println(p);
                    kdTree.insert(p);
                    pointSet.insert(p);
                }
        );
        Point2D pointToSearch = new Point2D(StdRandom.uniform(), StdRandom.uniform());
        Point2D resultBF = pointSet.nearest(pointToSearch);
        Point2D resultKT = kdTree.nearest(pointToSearch);
        System.out.println("? " + pointToSearch);
        System.out.println(resultBF);
        System.out.println(pointToSearch.distanceSquaredTo(resultBF));
        System.out.println(resultKT);
        System.out.println(pointToSearch.distanceSquaredTo(resultKT));
    }
}