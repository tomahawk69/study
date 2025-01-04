package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PriyankaAndToysTest {

    private PriyankaAndToys priyankaAndToys;

    @BeforeEach
    void setUp() {
        priyankaAndToys = new PriyankaAndToys();
    }

    @ParameterizedTest
    @MethodSource("simpleArguments")
    void simple(List<Integer> weights, int expected) {
        int result = priyankaAndToys.solve(weights);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> simpleArguments() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 21, 7, 12, 14, 21), 4
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 10, 11, 12, 13), 2
                )
        );
    }
}