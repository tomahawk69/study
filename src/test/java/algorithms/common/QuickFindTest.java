package algorithms.common;

import algorithms.common.quickfindunion.QuickFind;
import algorithms.common.quickfindunion.QuickFindUnion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QuickFindTest extends QuickFindUnionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findInit() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new QuickFind(n);
        testFind(quickFind, n);
    }

    @Test
    public void union() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new QuickFind(n);
        testUnion(quickFind, n, 4, 1, 7);
    }

    @Test
    public void unionRandom() throws Exception {
        int n = 10;
        QuickFindUnion quickFind = new QuickFind(n);
        testUnionRandom(quickFind, n);
    }

    @Test
    public void testBoundaryFind1() throws Exception {
        int n = 10;
        QuickFind quickFind = new QuickFind(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickFind.find(-1, 1);
    }

    @Test
    public void testBoundaryFind2() throws Exception {
        int n = 10;
        QuickFind quickFind = new QuickFind(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickFind.find(1, -1);
    }

    @Test
    public void testBoundaryUnion1() throws Exception {
        int n = 10;
        QuickFind quickFind = new QuickFind(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickFind.union(1, -1);
    }

    @Test
    public void testBoundaryUnion2() throws Exception {
        int n = 10;
        QuickFind quickFind = new QuickFind(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickFind.union(-1, 1);
    }

    @Test
    public void testBoundarySize() throws Exception {
        int n = 0;
        expectedException.expect(IllegalArgumentException.class);
        new QuickFind(n);
    }

    @Test
    public void testBoundarySizeNegative() throws Exception {
        int n = -10;
        expectedException.expect(IllegalArgumentException.class);
        new QuickFind(n);
    }
}