package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CountArraysTest {

    private CountArrays countArrays;

    @BeforeEach
    void setUp() {
        countArrays = new CountArrays();
    }

    public static Stream<Arguments> parametersProvider() {
        int[] a1 = {4, 3, 2};
        int[] a2 = {761, 99, 1};
        int[] a3 = {942, 77, 13};
        int[] a4 = {33260, 96055, 24745};
        return Stream.of(
                Arguments.of(
                        a1, 3L
                ),
                Arguments.of(
                        a2, 236568308L
                ),
                Arguments.of(
                        a3, 804842436L
                ),
                Arguments.of(
                        a4, 266262299
                )
        );
    }

    @ParameterizedTest
    @MethodSource("parametersProvider")
    void shouldReturnValidAnswers(int[] input, long expected) {
        long result = countArrays.countArray(input[0], input[1], input[2]);
        assertThat(result).isEqualTo(expected);
    }
}