package algorithms.tasks;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentsInGraphTest {

    @Test
    void case_() {
        int[][] input = new int[5][2];
        input[0][0] = 1;
        input[0][1] = 6;
        input[1][0] = 2;
        input[1][1] = 7;
        input[2][0] = 3;
        input[2][1] = 8;
        input[3][0] = 4;
        input[3][1] = 9;
        input[4][0] = 2;
        input[4][1] = 6;

        int[] expected = new int[2];
        expected[0] = 2;
        expected[1] = 4;

        int[] result = ComponentsInGraph.componentsInGraph(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void case1() {
        int[][] gb = loadGb("/componentsingraph/01.in");
        int[] expectedResult = loadResult("/componentsingraph/01.out");
        int[] result = ComponentsInGraph.componentsInGraph(gb);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void case2() {
        int[][] gb = loadGb("/componentsingraph/02.in");
        int[] expectedResult = loadResult("/componentsingraph/02.out");
        int[] result = ComponentsInGraph.componentsInGraph(gb);
        assertThat(result).isEqualTo(expectedResult);
    }

    private int[] loadResult(String resourceName) {
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
        int[] expectedResult = new int[2];
        try (Scanner scanner = new Scanner(inputStream)) {
            expectedResult[0] = scanner.nextInt();
            expectedResult[1] = scanner.nextInt();
        }
        return expectedResult;
    }

    private int[][] loadGb(String resourceName) {
        int[][] gb;
        InputStream inputStream = this.getClass().getResourceAsStream(resourceName);
        try (Scanner scanner = new Scanner(inputStream)) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            gb = new int[n][2];

            for (int gbRowItr = 0; gbRowItr < n; gbRowItr++) {
                String[] gbRowItems = scanner.nextLine().split(" ");

                for (int gbColumnItr = 0; gbColumnItr < 2; gbColumnItr++) {
                    int gbItem = Integer.parseInt(gbRowItems[gbColumnItr].trim());
                    gb[gbRowItr][gbColumnItr] = gbItem;
                }
            }
            return gb;
        }
    }
}