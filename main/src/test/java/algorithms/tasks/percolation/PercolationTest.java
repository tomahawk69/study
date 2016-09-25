package algorithms.tasks.percolation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.junit.Assert.*;

public class PercolationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Percolation loadResource(String resourceFileName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(resourceFileName)));
        String line = reader.readLine();
        Percolation result = new Percolation(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            if (line.trim().length() > 0) {
                String[] values = line.trim().split("\\s+");
                result.open(Integer.parseInt(values[0].trim()), Integer.parseInt(values[1].trim()));
            }
        }

        return result;
    }

    @Test
    public void isFullInput10() throws Exception {
        Percolation percolation = loadResource("percolation/input10.txt");
        assertTrue(percolation.isOpen(1, 4));
        assertFalse(percolation.isOpen(1, 3));
        assertTrue(percolation.isOpen(4, 10));
        assertTrue(percolation.isOpen(9, 10));
        assertFalse(percolation.isOpen(7, 8));
        assertTrue(percolation.percolates());
    }

    @Test
    public void isPercolates1() throws Exception {
        Percolation percolation = loadResource("percolation/input1.txt");
        assertTrue(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void isPercolates2() throws Exception {
        Percolation percolation = loadResource("percolation/input2.txt");
        assertTrue(percolation.percolates());
        percolation.open(1, 2);
        assertTrue(percolation.percolates());
    }

    @Test
    public void isPercolates2No() throws Exception {
        Percolation percolation = loadResource("percolation/input2-no.txt");
        assertFalse(percolation.percolates());
        percolation.open(1, 2);
        assertTrue(percolation.percolates());
        percolation = loadResource("percolation/input2-no.txt");
        assertFalse(percolation.percolates());
        percolation.open(2, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void isPercolates1No() throws Exception {
        Percolation percolation = loadResource("percolation/input1-no.txt");
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void failedN() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        Percolation percolation = new Percolation(0);
    }

    @Test
    public void failedIsFullIless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isFull(0, 1);
    }

    @Test
    public void failedIsFullIgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isFull(11, 1);
    }

    @Test
    public void failedIsFullJless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isFull(1, 0);
    }

    @Test
    public void failedIsFullJgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isFull(1, 11);
    }

    @Test
    public void failedIsOpenIless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isOpen(0, 1);
    }

    @Test
    public void failedIsOpenIgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isOpen(11, 1);
    }

    @Test
    public void failedIsOpenJless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isOpen(1, 0);
    }

    @Test
    public void failedIsOpenJgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.isOpen(1, 11);
    }

    @Test
    public void failedOpenIless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.open(0, 1);
    }

    @Test
    public void failedOpenIgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.open(11, 1);
    }

    @Test
    public void failedOpenJless0() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.open(1, 0);
    }

    @Test
    public void failedOpenJgreaterN() throws Exception {
        Percolation percolation = new Percolation(10);
        expectedException.expect(IndexOutOfBoundsException.class);
        percolation.open(1, 11);
    }

    @Test
    public void isFullInput10No() throws Exception {
        Percolation percolation = loadResource("percolation/input10-no.txt");
        assertFalse(percolation.isFull(1, 1));
        assertTrue(percolation.isOpen(1, 4));
        assertFalse(percolation.isOpen(1, 3));
        assertTrue(percolation.isOpen(4, 10));
        assertTrue(percolation.isOpen(9, 10));
        assertFalse(percolation.isOpen(7, 8));
        assertFalse(percolation.percolates());
    }

    @Test
    public void isFullInput31() throws Exception {
        Percolation percolation = loadResource("percolation/input31.txt");
        assertTrue(percolation.percolates());
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(2, 3));
        assertTrue(percolation.isFull(3, 3));
        assertTrue(percolation.isOpen(1, 3));
        assertTrue(percolation.isFull(2, 1));
    }

    @Test
    public void isFullInput32() throws Exception {
        Percolation percolation = loadResource("percolation/input32.txt");
        assertTrue(percolation.percolates());
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(2, 3));
        assertTrue(percolation.isFull(3, 3));
        assertTrue(percolation.isOpen(1, 3));
        assertFalse(percolation.isFull(2, 1));
    }

    @Test
    public void isFullInput6() throws Exception {
        Percolation percolation = loadResource("percolation/input6.txt");
        assertFalse(percolation.isFull(1, 1));
    }

    @Test
    public void isFullInput8() throws Exception {
        Percolation percolation = loadResource("percolation/input8.txt");
        assertFalse(percolation.isFull(1, 1));
    }

    @Test
    public void isFullInput8No() throws Exception {
        Percolation percolation = loadResource("percolation/input8-no.txt");
        assertFalse(percolation.isFull(1, 1));
    }

    @Test
    public void isFullheart25() throws Exception {
        Percolation percolation = loadResource("percolation/heart25.txt");
        assertFalse(percolation.isFull(1, 1));
    }

}