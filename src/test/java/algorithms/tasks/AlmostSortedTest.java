package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AlmostSortedTest {

    private AlmostSorted almostSorted;

    @BeforeEach
    void setUp() {
        almostSorted = new AlmostSorted();
    }

    @ParameterizedTest
    @MethodSource("validParameters")
    void shouldReturnExpectedResult(List<Integer> parameters, List<String> expected) {
        List<String> result = almostSorted.almostSorted(parameters);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void testInput1() throws IOException {
        String name = "/almostSorted/input1.txt";
        try (InputStream is = this.getClass().getResourceAsStream(name);
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isReader)) {
            List<Integer> input = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::valueOf).toList();
            List<String> result = almostSorted.almostSorted(input);
            for (int i = 1; i < input.size(); i++) {
                if (input.get(i - 1) > input.get(i)) {
                    System.out.println(input.get(i - 1) + " > " + input.get(i));
                }
            }

            assertThat(result).isEqualTo(List.of("no"));
        }
    }

    public static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(List.of(4, 2), List.of("yes", "swap 1 2"))
        );
    }

}