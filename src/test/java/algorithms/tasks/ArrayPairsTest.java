package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayPairsTest {

    private ArrayPairs arrayPairs;

    @BeforeEach
    void setUp() {
        arrayPairs = new ArrayPairs();
    }

    @Test
    void test1() {
        List<Integer> input = List.of(1, 1, 2, 4, 2);
        long expected = 8;
        long result = arrayPairs.solve(input);
        assertThat(result).isEqualTo(expected);
    }
}