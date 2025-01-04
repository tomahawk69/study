package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LatinConversionTest {

    private LatinConversion latinConversion;

    @BeforeEach
    void setUp() {
        latinConversion = new LatinConversion();
    }

    @ParameterizedTest
    @MethodSource("validCases")
    void shouldProceedValidCases(int input, String expected) {
        String result = latinConversion.intToRoman(input);
        assertThat(result).isNotNull().isEqualTo(expected);
    }

    public static Stream<Arguments> validCases() {
        return Stream.of(
                Arguments.of(40, "XL"),
                Arguments.of(9, "IX"),
                Arguments.of(3749, "MMMDCCXLIX")
        );
    }

}