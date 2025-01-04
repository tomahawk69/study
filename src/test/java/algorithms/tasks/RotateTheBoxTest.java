package algorithms.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RotateTheBoxTest {

    private RotateTheBox rotateTheBox;

    @BeforeEach
    void setUp() {
        rotateTheBox = new RotateTheBox();
    }

    @Test
    void simpleCase1() {
        char[][] input = {{'#', '.', '#'}};
        char[][] expected = {{'.'}, {'#'}, {'#'}};
        char[][] result = rotateTheBox.rotateTheBox(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void simpleCase2() {
        char[][] input = {{'#', '.', '*', '.'}, {'#', '#', '*', '.'}};
        char[][] expected = {{'#', '.'}, {'#', '#'}, {'*', '*'}, {'.', '.'}};
        char[][] result = rotateTheBox.rotateTheBox(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void simpleCase3() {
        char[][] input = {{'#','#','*','.','*','.'},{'#','#','#','*','.','.'},{'#','#','#','.','#','.'}};
        char[][] expected = {{'.','#','#'},{'.','#','#'},{'#','#','*'},{'#','*','.'},{'#','.','*'},{'#','.','.'}};
        char[][] result = rotateTheBox.rotateTheBox(input);

        assertThat(result).isEqualTo(expected);
    }


}