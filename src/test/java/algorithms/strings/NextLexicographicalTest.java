package algorithms.strings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NextLexicographicalTest {

    private NextLexicographical nextLexicographical;

    @BeforeEach
    void setUp() {
        nextLexicographical = new NextLexicographical();
    }

    @Test
    void testCases1() throws IOException {
        List<String> input = loadInput("01");
        List<String> expected = loadInput("01.expected");
        for (int i = 0; i < input.size(); i++) {
            String result = nextLexicographical.solve(input.get(i));
            assertThat(result).as(input.get(i)).isEqualTo(expected.get(i));
        }
    }

    private List<String> loadInput(String base) throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/nextlexicographical/" + base + ".txt");
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            return br.lines().toList();
        }
    }
}