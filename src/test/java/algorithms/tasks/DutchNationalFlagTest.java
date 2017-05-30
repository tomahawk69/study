package algorithms.tasks;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DutchNationalFlagTest {
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Rule
    public MockitoRule mrule = MockitoJUnit.rule();

    private int[] createRandomItems(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> random.nextInt(3)).mapToInt(i -> i).toArray();
    }

    @Test
    public void sort() throws Exception {
        int size = 100;
        int[] items = createRandomItems(size);
        DutchNationalFlag service = spy(new DutchNationalFlag(items));
        service.sort();
        int i = 0;
        while (i < size && items[i++] == 0) ;
        assertTrue(items[i] == 1);
        while (i < size && items[i++] == 1) ;
        assertTrue(items[i] == 2);
        while (i < size && items[i++] == 2) ;
        assertEquals(i, size);
        verify(service, atMost(size)).getColor(anyInt());
        verify(service, atMost(size)).swap(anyInt(), anyInt());
    }

}