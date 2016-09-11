package algorithms.common;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class QuickUnionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findInit() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new QuickUnion(n);
        tester.testFind(quickFind, n);
    }

    @Test
    public void union() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new QuickUnion(n);
        tester.testUnion(quickFind, n, 4, 1, 7);
    }

    @Test
    public void unionRandom() throws Exception {
        QuickFindUnionTest tester = new QuickFindUnionTest();
        int n = 10;
        QuickFindUnion quickFind = new QuickUnion(n);
        tester.testUnionRandom(quickFind, n);
    }

    @Test
    public void testBoundaryFind1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new QuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(-1, 1);
    }

    @Test
    public void testBoundaryFind2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new QuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.find(1, -1);
    }

    @Test
    public void testBoundaryUnion1() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new QuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(1, -1);
    }

    @Test
    public void testBoundaryUnion2() throws Exception {
        int n = 10;
        QuickUnion quickUnion = new QuickUnion(n);
        expectedException.expect(ArrayIndexOutOfBoundsException.class);
        quickUnion.union(-1, 1);
    }

    @Test
    public void testBoundarySize() throws Exception {
        int n = 0;
        expectedException.expect(IllegalArgumentException.class);
        new QuickUnion(n);
    }

    @Test
    public void testBoundarySizeNegative() throws Exception {
        int n = -10;
        expectedException.expect(IllegalArgumentException.class);
        new QuickUnion(n);
    }

    @Test
    public void testDept() throws Exception {
        int size = 65;
        QuickUnion quickUnion = new QuickUnion(size);

        int i = 0;
        int k = 1;
        do {
            for (int j = k; j < size; j += k * 2) {
                quickUnion.union(j - 1, j);
            }
            i++;
            if (Math.round(Math.pow(2, i)) > size) {
                i = size;
            } else {
                k = Math.toIntExact(Math.round(Math.pow(2, i)));
            }
        }
        while (Math.pow(2, i) < size);
    }
}