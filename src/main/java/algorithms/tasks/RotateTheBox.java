package algorithms.tasks;

import java.util.Arrays;

public class RotateTheBox {

    public char[][] rotateTheBox(char[][] boxGrid) {
        char[][] result = new char[boxGrid[0].length][boxGrid.length];
        for (int i = 0; i < boxGrid.length; i++) {
            for (int j = 0; j < boxGrid[i].length; j++) {
                result[j][boxGrid.length - 1 - i] = boxGrid[i][j];
            }
        }
        System.out.println(Arrays.deepToString(result));
        for (int i = 0; i < result[0].length; i++) {
            int y = result.length - 1;
            int bottomObstacle = result.length;
            while (y >= 0) {
                if (result[y][i] == '*') {
                    bottomObstacle = y;
                } else if (result[y][i] == '#') {
                    if (y < bottomObstacle - 1) {
                        result[y][i] = '.';
                        result[bottomObstacle - 1][i] = '#';
                    }
                    bottomObstacle = bottomObstacle - 1;
                }
                y--;
            }
        }
        return result;
    }

    /*
    [[#, #], [., #], [*, *], [., .]]
    [[., #], [#, #], [*, *], [., .]]
     */

}
