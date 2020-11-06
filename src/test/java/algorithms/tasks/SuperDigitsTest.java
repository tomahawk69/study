package algorithms.tasks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuperDigitsTest {

    @Test
    void case1() {
        String n = "148";
        int k = 3;
        int expected = 3;
        int result = SuperDigits.superDigit(n, k);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void case2() {
        String n = "9875";
        int k = 4;
        int expected = 8;
        int result = SuperDigits.superDigit(n, k);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void case3() {
        String n = "123";
        int k = 3;
        int expected = 9;
        int result = SuperDigits.superDigit(n, k);
        assertThat(result).isEqualTo(expected);
    }
}