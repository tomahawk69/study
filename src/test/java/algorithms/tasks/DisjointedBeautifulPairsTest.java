package algorithms.tasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DisjointedBeautifulPairsTest {

    private DisjointedBeautifulPairs disjointedBeautifulPairs = new DisjointedBeautifulPairs();

    @ParameterizedTest
    @MethodSource("simpleCases")
    void shouldSolveSimpleCase(List<Integer> a, List<Integer> b, int expected) {
        int result = disjointedBeautifulPairs.solve(a, b);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> simpleCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 3), 4),
                Arguments.of(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4), 3),
                Arguments.of(List.of(3, 5, 7, 11, 5, 8), List.of(5, 7, 11, 10, 5, 8), 6)
        );
    }
}