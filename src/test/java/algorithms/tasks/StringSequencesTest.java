package algorithms.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StringSequencesTest {


    @Test
    void shouldFindFirst1() {
        String dictionary = "owe";
        String input = "owe";
        String[] expectedResult = {};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find1(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldFindFirst2() {
        String dictionary = "owe";
        String input = "owe";
        String[] expectedResult = {};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find2(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldFindOne1() {
        String dictionary = "owe";
        String input = "I owe you";
        String[] expectedResult = {"I", "you"};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find1(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldFindOne2() {
        String dictionary = "owe";
        String input = "I owe you";
        String[] expectedResult = {"I", "you"};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find2(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldFindSeveral1() {
        String dictionary = "one two three";
        String input = "trash1 one trash2 two trash3 three trash4 three trash5";
        String[] expectedResult = {"trash1", "trash2", "trash3", "three", "trash4", "trash5"};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find1(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldFindSeveral2() {
        String dictionary = "one two three";
        String input = "trash1 one trash2 two trash3 three trash4 three trash5";
        String[] expectedResult = {"trash1", "trash2", "trash3", "trash4", "trash5"};
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find2(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldNotFind1() {
        String dictionary = "it is";
        String input = "My name it suppose";
        String[] expectedResult = input.split("\\s+");
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find1(input);

        assertArrayEquals(expectedResult, result);
    }

    @Test
    void shouldNotFind2() {
        String dictionary = "it is";
        String input = "My name it suppose";
        String[] expectedResult = input.split("\\s+");
        StringSequences stringSequences = new StringSequences(dictionary);

        String[] result = stringSequences.find2(input);

        assertArrayEquals(expectedResult, result);
    }
}