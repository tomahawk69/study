package algorithms.common;

import algorithms.common.quickfindunion.QuickFindUnion;
import algorithms.common.quickfindunion.QuickUnion;
import algorithms.common.quickfindunion.WeightedQuickUnion;
import algorithms.common.quickfindunion.WeightedQuickUnionWithPathCompression;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WeightedQuickUnionTest extends QuickFindUnionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findInit() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnion(n);
        testFind(quickFind, n);
    }

    @Test
    public void union() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnion(n);
        testUnion(quickFind, n, 4, 1, 7);
    }

    @Test
    public void unionRandom() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnion(n);
        testUnionRandom(quickFind, n);
    }

    @Test
    public void testBoundaryFind1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(-1, 1);
    }

    @Test
    public void testBoundaryFind2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(1, -1);
    }

    @Test
    public void testBoundaryUnion1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(1, -1);
    }

    @Test
    public void testBoundaryUnion2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(-1, 1);
    }

    @Test
    public void testBoundarySize() throws Exception {
        int n = 0;
        expectedException.expect(IllegalArgumentException.class);
        new WeightedQuickUnion(n);
    }

    @Test
    public void testBoundarySizeNegative() throws Exception {
        int n = -10;
        expectedException.expect(IllegalArgumentException.class);
        new WeightedQuickUnion(n);
    }

    @Test
    public void testWeight() throws Exception {
        int n = 10;
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(n);
        assertEquals(1, weightedQuickUnion.getWeight(1));
        weightedQuickUnion.union(0, 1);
        assertEquals(1, weightedQuickUnion.getWeight(0));
        assertEquals(2, weightedQuickUnion.getWeight(1));
        weightedQuickUnion.union(0, 3);
        assertEquals(1, weightedQuickUnion.getWeight(0));
        assertEquals(3, weightedQuickUnion.getWeight(1));
        assertEquals(1, weightedQuickUnion.getWeight(3));
        weightedQuickUnion.union(6, 7);
        weightedQuickUnion.union(6, 3);
        assertEquals(5, weightedQuickUnion.getWeight(1));
        assertEquals(2, weightedQuickUnion.getWeight(7));
    }

    @Test
    public void testDept() throws Exception {
        QuickUnion quickUnion = new WeightedQuickUnionWithPathCompression(32);

        quickUnion.union(0, 1);
        quickUnion.union(2, 3);
        quickUnion.union(4, 5);
        quickUnion.union(6, 7);

        quickUnion.union(2, 1);
        quickUnion.union(6, 5);

        quickUnion.union(3, 4);

        quickUnion.union(8, 3);

        assertEquals(1, quickUnion.depth(8));
        assertEquals(0, quickUnion.depth(5));
        assertTrue(quickUnion.maxDepth() < 3);
    }
}