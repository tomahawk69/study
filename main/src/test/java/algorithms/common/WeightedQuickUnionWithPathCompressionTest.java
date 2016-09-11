package algorithms.common;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WeightedQuickUnionWithPathCompressionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findInit() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnionWithPathCompression(n);
        tester.testFind(quickFind, n);
    }

    @Test
    public void union() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnionWithPathCompression(n);
        tester.testUnion(quickFind, n, 4, 1, 7);
    }

    @Test
    public void unionRandom() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new WeightedQuickUnionWithPathCompression(n);
        tester.testUnionRandom(quickFind, n);
    }

    @Test
    public void testBoundaryFind1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnionWithPathCompression(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(-1, 1);
    }

    @Test
    public void testBoundaryFind2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnionWithPathCompression(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(1, -1);
    }

    @Test
    public void testBoundaryUnion1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnionWithPathCompression(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(1, -1);
    }

    @Test
    public void testBoundaryUnion2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new WeightedQuickUnionWithPathCompression(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(-1, 1);
    }

    @Test
    public void testBoundarySize() throws Exception {
        int n = 0;
        expectedException.expect(IllegalArgumentException.class);
        new WeightedQuickUnionWithPathCompression(n);
    }

    @Test
    public void testBoundarySizeNegative() throws Exception {
        int n = -10;
        expectedException.expect(IllegalArgumentException.class);
        new WeightedQuickUnionWithPathCompression(n);
    }

    @Test
    public void testWeight() throws Exception {
        int n = 10;
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnionWithPathCompression(n);
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
}