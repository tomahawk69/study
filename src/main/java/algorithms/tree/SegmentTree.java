package algorithms.tree;

import java.util.List;

public class SegmentTree {

    private int[] tree;

    public static SegmentTree buildTree(int[] input) {
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.build(input);
        return segmentTree;
    }

    public static SegmentTree buildTree(List<Integer> input) {
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.build(input);
        return segmentTree;
    }

//    public int queryUtil(int i, int sl, int sr, int ql, int qr) {
//        if (ql <= sl && qr >= sr) {
//            return tree[i];
//        }
//        if (qr < sl || ql > sr) {
//            return Integer.MIN_VALUE;
//        }
//        int mid = (sl + sr) / 2;
//        return Math.max(queryUtil(2 * i + 1, sl, mid, ql, qr),
//                queryUtil(2 * i + 2, mid + 1, sr, ql, qr));
//    }

    public int querySegmentTree(int i, int left, int right, int l, int r) {
        if (r < left || right < l) {
            return 0;
        }

        if (l <= left && right <= r) {
            return tree[i];
        }

        int mid = left + (right - left) / 2;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        int leftMax = querySegmentTree(leftChild, left, mid, l, r);
        int rightMax = querySegmentTree(rightChild, mid + 1, right, l, r);

        return Math.max(leftMax, rightMax);
    }

    private static int calcArraySize(int n) {
//        return (int) Math.ceil(n * Math.log(n));
        return 4 * n;
    }

    private void build(int[] arr) {
        int n = arr.length;
        int max = calcArraySize(n);
        int[] result = new int[max];
        buildSegmentTree(arr, result, 0, 0, n - 1);
        tree = result;
    }

    private void build(List<Integer> arr) {
        int n = arr.size();
        int max = calcArraySize(n);
        int[] result = new int[max];
        buildSegmentTree(arr, result, 0, 0, n - 1);
        tree = result;
    }

    private void buildSegmentTree(int[] arr, int[] tree, int i, int left, int right) {
        if (left == right) {
            tree[i] = arr[left];
        } else {
            int mid = (left + right) / 2;
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            buildSegmentTree(arr, tree, leftChild, left, mid);
            buildSegmentTree(arr, tree, rightChild, mid + 1, right);
            tree[i] = Math.max(tree[leftChild], tree[rightChild]);
        }
    }

    private void buildSegmentTree(List<Integer> arr, int[] tree, int i, int left, int right) {
        if (left == right) {
            tree[i] = arr.get(left);
        } else {
            int mid = (left + right) / 2;
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            buildSegmentTree(arr, tree, leftChild, left, mid);
            buildSegmentTree(arr, tree, rightChild, mid + 1, right);
            tree[i] = Math.max(tree[leftChild], tree[rightChild]);
        }
    }

    public int[] getTree() {
        return tree;
    }
}
