package algorithms.tasks.test;

import java.util.HashMap;
import java.util.Map;

public class MyMapDecoder {

    public static final String PAIRS_DELIMITER = "&";
    public static final String KEY_VALUE_DELIMITER = "=";

    /**
     * Ambiguous description:
     * - no definition of invalid inut. assumed: empty both key AND value; absent key value delimiter
     * - duplicates? assumed not an issue, last value should be used
     * - & in the end of input; assumed not an issue
     */
    public Map<String, String> decode(String s) {
        if (s == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        if (!s.isEmpty()) {
            String[] pairs = s.split(PAIRS_DELIMITER);
            for (String pair : pairs) {
                int pos = pair.indexOf(KEY_VALUE_DELIMITER);
                if (pos < 0) {
                    throw new IllegalArgumentException(String.format("Invalid input key-value pair: key value delimiter '%s' is missing in %s", KEY_VALUE_DELIMITER, pair));
                }
                String key = pair.substring(0, pos);
                String value = pair.substring(pos + 1);
                if (key.isEmpty() && value.isEmpty()) {
                    throw new IllegalArgumentException("Invalid input key-value pair: both key and value are empty in " + pair);
                }
                result.put(key, value);
            }
        }
        return result;
    }

}
