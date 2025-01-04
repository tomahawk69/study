package algorithms.tasks;

import algorithms.tree.SegmentTree;

import java.util.List;

public class ArrayPairs {
    public long solve(List<Integer> arr) {
        long result = 0;
        int n = arr.size();

        SegmentTree tree = SegmentTree.buildTree(arr);

        for (int i = 0; i < arr.size(); i++) {
            int low = i + 1;
            int high = n - 1;
            int bestJ = i;

            while (low <= high) {
                int mid = (low + high) / 2;
                int maxVal = tree.querySegmentTree(0, 0, n - 1, i, mid);

                if (arr.get(i) * arr.get(mid) <= maxVal) {
                    bestJ = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            if (bestJ > i) {
                result += (bestJ - i);
            }
        }

        return result;
    }

}
