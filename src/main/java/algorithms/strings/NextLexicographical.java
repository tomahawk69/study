package algorithms.strings;

import java.util.Arrays;

public class NextLexicographical {

    public String solve(String w) {
        char[] result = w.toCharArray();
        int left = -1;
        int charRight = result.length;
        for (int i = result.length - 1; i > 0; i--) {
            char current = result[i];
            for (int j = i - 1; j >= 0; j--) {
                if (j <= left) {
                    break;
                }
                if (result[j] >= current) {
                    continue;
                }
                charRight = i;
                left = j;
                break;
            }
        }
        if (left >= 0) {
            char tmp = result[charRight];
            result[charRight] = result[left];
            result[left] = tmp;
            Arrays.sort(result, left + 1, result.length);
            return new String(result);
        } else {
            return "no answer";
        }
    }

}
