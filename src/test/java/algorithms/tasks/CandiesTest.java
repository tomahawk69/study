package algorithms.tasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CandiesTest {

    private Candies candies = new Candies();

    @ParameterizedTest
    @MethodSource("childrenInputSimple")
    void shouldPassSimpleCase(List<Integer> children, long expected) {
        long result = candies.candies(children);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource("candies_01.txt")
    void shouldPassComplexCase(String fileName) throws IOException {
        String name = "/candies/" + fileName;
        List<Integer> children;
        int count;
        long expected;
        try (InputStream is = this.getClass().getResourceAsStream(name);
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isReader)) {
            count = Integer.parseInt(reader.readLine());
            children = new ArrayList<>(count);
            expected = Long.parseLong(reader.readLine());
            String line = reader.readLine();
            while (line != null) {
                children.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        }
        long result = candies.candies(children);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> childrenInputSimple() {
        return Stream.of(
                Arguments.of(List.of(4, 6, 4, 5, 6, 2), 10L),
                Arguments.of(List.of(2, 4, 2, 6, 1, 7, 8, 9, 2, 1), 19L), // 1 2 1 2 1 2 3 4 2 1
                Arguments.of(List.of(1, 2, 2), 4L) // 1 2 2
        );
    }
}