package algorithms.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Look for the longest occurrence of template string (dictionary) in the input string
 * Occurrences may be divided by other words
 */
public class StringSequences {
    private final String[] dictionary;

    public StringSequences(String dictionaryStr) {
        this.dictionary = dictionaryStr.split("\\s+");
    }

    /**
     * Return:
     * - all words from beginning to the first dictionary word occurrence
     * - all words from last dictionary word occurrence to the end
     * - intermediate words between every two words from dictionary
     * - all words if occurrence doesn't found
     */
    public String[] find1(String input) {
        String[] inputArray = input.split("\\s+");
        List<String> result = new LinkedList<>();
        int start = -1;
        while (start++ < inputArray.length) {
            if (inputArray[start].equals(dictionary[0])) break;
            result.add(inputArray[start]);
        }
        if (start < inputArray.length) {
            int end = start + 1;
            for (int i = 1; i < dictionary.length; i++) {
                while (end < inputArray.length) {
                    end++;
                    if (inputArray[end - 1].equals(dictionary[i])) break;
                    result.add(inputArray[end -1]);
                }
                if (end == inputArray.length) {
                    return inputArray;
                }
            }
            int newEnd = end;
            for (int i = end; i < inputArray.length; i++) {
                if (inputArray[i].equals(dictionary[dictionary.length - 1])) newEnd = i + 1;
            }
            result.addAll(Arrays.asList(inputArray).subList(end - 1, newEnd - 1));
            result.addAll(Arrays.asList(inputArray).subList(newEnd, inputArray.length));
            return result.toArray(new String[0]);
        }
        return inputArray;
    }

    /**
     * - all words from beginning to the first dictionary word occurrence
     * - all words from last dictionary word occurrence to the end
     * - intermediate words that don't exists in the dictionary
     * - all words if occurrence doesn't found
     */
    public String[] find2(String input) {
        String[] inputArray = input.split("\\s+");
        List<String> result = new LinkedList<>();
        int start = -1;
        int end = -1;
        int i = 0;
        int j = 0;
        boolean found = false;

        while (i < inputArray.length) {
            if (inputArray[i].equals(dictionary[j])) {
                if (start < 0) {
                    start = i;
                    end = i;
                } else {
                    end = i;
                }
                found = found || j == dictionary.length - 1;
                if (!found) j++;
            }
            i++;
        }
        if (found) {
            result.addAll(Arrays.asList(inputArray).subList(0, start));
            if (start < end) {
                List<String> intermediate = new ArrayList<>(Arrays.asList(inputArray).subList(start + 1, end));
                intermediate.removeAll(Arrays.asList(dictionary));
                result.addAll(intermediate);
            }
            result.addAll(Arrays.asList(inputArray).subList(end + 1, inputArray.length));
            return result.toArray(new String[0]);
        }
        return inputArray;
    }

}
