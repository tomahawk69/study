package algorithms.tasks;

import java.util.ArrayList;
import java.util.List;

public class AlmostSorted {

    public List<String> almostSorted(List<Integer> arr) {
        int anomalyCount = 0;
        boolean anomalySeria = false;
        boolean fixed = false;
        int right = -1;
        FixResult result = null;
        for (int i = arr.size() - 2; i >= 0; i--) {
            if (arr.get(i) > arr.get(i + 1)) {
                anomalyCount++;
                if (anomalySeria) {
                    continue;
                } else if (fixed) {
                    // only one fix allowed
                    result = FixResult.impossible();
                    break;
                }
                if (right > 0) {
                    result = trySwap(arr, i, right);
                    if (result != null) {
                        anomalyCount = 0;
                        right = 0;
                        fixed = true;
                        continue;
                    } else {
                        // second anomaly not in a row, further fix impossible
                        result = FixResult.impossible();
                        break;
                    }
                }
                right = i + 1;
                anomalySeria = true;
            } else {
                if (anomalyCount > 0) {
                    if (anomalyCount == 1) {
                        if (fixed) {
                            result = FixResult.impossible();
                            break;
                        }
                        result = trySwap(arr, i + 1, right);
                    }
                    if (anomalySeria) {
                        anomalySeria = false;
                        if (result == null) {
                            result = tryReverse(arr, i + 1, right);
                        }
                    }
                    if (result != null) {
                        fixed = true;
                        anomalyCount = 0;
                    }
                }
            }
        }
        if (anomalySeria && (result == null || result.fix != Fix.IMPOSSIBLE)) {
            if (anomalyCount == 1) {
                result = trySwap(arr, 0, right);
            }
            if (result == null) {
                result = tryReverse(arr, 0, right);
            }
            if (result != null) {
                anomalyCount = 0;
            }
        }
        if (anomalyCount > 0) {
            return List.of("no");
        } else {
            List<String> results = new ArrayList<>();
            results.add("yes");
            if (result != null) {
                results.add(result.fix.name + " " + (result.left + 1) + " " + (result.right + 1));
            }
            return results;
        }
    }

    private static FixResult trySwap(List<Integer> arr, int left, int right) {
        if ((right == arr.size() - 1 || arr.get(left) <= arr.get(right + 1)) &&
                (left == 0 || arr.get(left - 1) <= arr.get(right)) &&
                (right - left <= 1 || arr.get(left + 1) >= arr.get(right)) &&
                (right - left <= 1 || arr.get(right - 1) <= arr.get(left))
        ) {
            return new FixResult(Fix.SWAP, left, right);
        }
        return null;
    }

    private static FixResult tryReverse(List<Integer> arr, int left, int right) {
        if ((right == arr.size() -1 || arr.get(left) <= arr.get(right + 1)) &&
                (left == 0 || arr.get(right) >= arr.get(left - 1))) {
            return new FixResult(Fix.REVERSE, left, right);
        }
        return null;
    }

    private enum Fix {
        SWAP("swap"), REVERSE("reverse"), IMPOSSIBLE("");

        final String name;

        Fix(String name) {
            this.name = name;
        }
    }

    private record FixResult(Fix fix, Integer left, Integer right) {
            static FixResult impossible() {
                return new FixResult(Fix.IMPOSSIBLE, null, null);
            }
    }
}
