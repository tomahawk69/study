package algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SegmentTreeTest {

    @Test
    void test1() {
        int[] input = {1, 2, 3, 4, 5};
        SegmentTree segmentTree = SegmentTree.buildTree(input);
        System.out.println(Arrays.toString(segmentTree.getTree()));
    }

    @Test
    void test2() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        SegmentTree segmentTree = SegmentTree.buildTree(input);
        System.out.println(Arrays.toString(segmentTree.getTree()));
    }
}