package algorithms.tasks.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsequences {

    static String[] missingWords(String s, String t) {

        String[] dictionary = t.split("\\s+");
        String[] inputArray = s.split("\\s+");

        List<String> result = new LinkedList<>();

        int start = -1;
        int end = -1;

        int i = 0;
        int j = 0;
        boolean isEnd = false;

        while (i < inputArray.length) {
            if (inputArray[i].equals(dictionary[j])) {
                if (start < 0) {
                    start = i;
                    end = i; // just in case if dictionary is 1-word
                } else {
                    end = i;
                }
                isEnd = isEnd || j == dictionary.length - 1; // found last item - however we'll continue to look until the end of input array
                if (!isEnd) j++;
            }
            i++;
        }

        // guaranteed that is a subsequence of s - we don't any check

        // add words from the 0 to start index
        result.addAll(Arrays.asList(inputArray).subList(0, start));

        // add intermediate words
        if (start < end) {
            List<String> intermediate = new ArrayList<>(Arrays.asList(inputArray).subList(start + 1, end));
            intermediate.removeAll(Arrays.asList(dictionary));
            result.addAll(intermediate);
        }

        // add words from the end index to end of input
        result.addAll(Arrays.asList(inputArray).subList(end + 1, inputArray.length));

        return result.toArray(new String[0]);
    }

    static String[] missingWords2(String s, String t) {
        String[] dictionary = t.split("\\s+");
        String[] inputArray = s.split("\\s+");

        List<String> result = new LinkedList<>();

        int i = 0;
        int j = 0;
        boolean isEnd = false;

        while (i < inputArray.length) {
            if (inputArray[i].equals(dictionary[j])) {
                isEnd = isEnd || j == dictionary.length - 1; // found last item - however we'll continue to look until the end of input array
                if (!isEnd) j++;
            } else {
                result.add(inputArray[i]);
            }
            i++;
        }

        return result.toArray(new String[0]);
    }

}
