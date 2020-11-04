package algorithms.tasks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PowerSumTest {

    @Test
    void testCase1() {
        int x = 10;
        int y = 2;
        int expected = 1;
        int result = PowerSum.powerSum(x, y);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testCase2() {
        int x = 100;
        int y = 2;
        int expected = 3;
        int result = PowerSum.powerSum(x, y);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testCase3() {
        int x = 100;
        int y = 3;
        int expected = 1;
        int result = PowerSum.powerSum(x, y);
        assertThat(result).isEqualTo(expected);
    }
}