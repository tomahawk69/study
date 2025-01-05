package algorithms.tasks;

import java.util.ArrayList;
import java.util.List;

public class ArrayPairs {


    public long solve(List<Integer> arr) {
        long result = 0;
        List<Integer> tmp = new ArrayList<>();
        int oneCount = 0;
        // remove all 1s, as it is special case and add them to result
        for (int i : arr) {
            if (i == 1) {
                result += (arr.size() - 1 - oneCount++);
            } else {
                tmp.add(i);
            }
        }
        return result + solve(tmp, 0, tmp.size());
    }

    public long solveNaive(List<Integer> arr) {
        int result = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            int max = arr.get(i);
            for (int j = i + 1; j < arr.size(); j++) {
                max = Math.max(max, arr.get(j));
                if (1l * arr.get(i) * arr.get(j) <= max) { // to prevent int overflow, cast to long
                    result++;
                }
            }
        }
        return result;
    }

    private static long solve(List<Integer> arr, int left, int right) {
        if (right - left < 2) {
            // array is of size 0 or 1
            return 0;
        } else if (right - left == 2) {
            // array is of size 2
            if (arr.get(left) == 1 || arr.get(right - 1) == 1) {
                // if any element is 1, they male a valid pair
                return 1;
            } else {
                return 0;
            }
        }
        long result = 0;
        int posLeft = left;
        int posRight = left;
        int max = arr.get(left);
        int i = left;
        // find maximum range posLeft - posRight, inclusive
        // it can be inefficient, as max can be at the beginning or at the end;
        int mid = (right - left) / 2;
        while (i < right) {
            Integer current = arr.get(i);
            if (current == max && posRight == i - 1) {
                posRight = i;
            } else if (current == max) {
                // there can be multiple max results, and it makes sense to choose the near-middle position to make divide-and-conquer near to O(n lon g)
                // in the worst case it can be O(n^2)
                if (Math.abs(mid - i) < Math.abs(posRight - mid)) {
                    posLeft = i;
                    posRight = i;
                }
            } else if (current > max) {
                posLeft = i;
                posRight = i;
                max = current;
            }
            i++;
        }

        List<Integer> leftArr = mergeSort(arr, left, posLeft);
        List<Integer> rightArr = mergeSort(arr, posRight + 1, right);
        int first = leftArr.size() - 1;
        int second = 0;

        // starting from last right and first left
        while (first >= 0 && second < rightArr.size()) {
            if (leftArr.get(first) == 1) {
                break;
            }
            if (rightArr.get(second) == 1) {
                second++;
                continue;
            }
            if (1L * leftArr.get(first) * rightArr.get(second) <= max) {
                result += first + 1;
                second++;
            } else if (rightArr.get(second) < max) {
                first--;
            } else {
                break;
            }
        }
        return result + solve(arr, left, posLeft) + solve(arr, posRight + 1, right);
    }


    public static List<Integer> mergeSort(List<Integer> input, int start, int end) {
        // Base case: if the range contains 1 or fewer elements, it's already sorted
        if (end - start <= 1) {
            return new ArrayList<>(input.subList(start, end));
        }

        // Calculate the middle index
        int middle = start + (end - start) / 2;

        // Recursively sort each half
        List<Integer> sortedLeft = mergeSort(input, start, middle);
        List<Integer> sortedRight = mergeSort(input, middle, end);

        // Merge the sorted halves
        return merge(sortedLeft, sortedRight);
    }

    // Merge method to combine two sorted lists
    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Merge elements from both lists in sorted order
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        // Append any remaining elements from the left list
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        // Append any remaining elements from the right list
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

}
