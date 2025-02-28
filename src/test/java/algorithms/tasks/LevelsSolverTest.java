package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LevelsSolverTest {

    private LevelsSolver levelsSolver;

    @BeforeEach
    void setUp() {
        levelsSolver = new LevelsSolver();
    }

    @ParameterizedTest
    @MethodSource("params")
    void givenList_whenSolve_thenSolve(List<Integer> levels, int expected) {
        int result = levelsSolver.solve(new ArrayList<>(levels));
        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), 10),
                Arguments.of(List.of(1, 1, 1, 4, 5), 3),
                Arguments.of(List.of(1, 1, 1, 1, 1), 0),
                Arguments.of(List.of(1, 2, 2, 2, 3), 5)
        );
    }

}