package algorithms.tasks.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubsequencesTest {

    @Test
    void shouldDo1() {
        String input = "I am using HackerRank to improve programming";
        String sequence = "am HackerRank to improve";
        String[] expectedResult = {"I", "using", "programming"};

        String[] result = Subsequences.missingWords(input, sequence);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldDo2() {
        String input = "I am using HackerRank to improve programming";
        String sequence = "am HackerRank to improve";
        String[] expectedResult = {"I", "using", "programming"};

        String[] result = Subsequences.missingWords2(input, sequence);
        assertArrayEquals(expectedResult, result);
    }
}