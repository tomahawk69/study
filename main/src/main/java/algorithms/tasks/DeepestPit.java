package algorithms.tasks;

public class DeepestPit {

    /**
     A non-empty zero-indexed array A consisting of N integers is given. A pit in this array is any triplet of integers (P, Q, R) such that:

     0 ≤ P < Q < R < N;
     sequence [A[P], A[P+1], ..., A[Q]] is strictly decreasing,
     i.e. A[P] > A[P+1] > ... > A[Q];
     sequence A[Q], A[Q+1], ..., A[R] is strictly increasing,
     i.e. A[Q] < A[Q+1] < ... < A[R].
     The depth of a pit (P, Q, R) is the number min{A[P] − A[Q], A[R] − A[Q]}.

     For example, consider array A consisting of 10 elements such that:

     A[0] =  0
     A[1] =  1
     A[2] =  3
     A[3] = -2
     A[4] =  0
     A[5] =  1
     A[6] =  0
     A[7] = -3
     A[8] =  2
     A[9] =  3
     Triplet (2, 3, 4) is one of pits in this array, because sequence [A[2], A[3]] is
     strictly decreasing (3 > −2) and sequence [A[3], A[4]] is strictly increasing (−2 < 0).
     Its depth is min{A[2] − A[3], A[4] − A[3]} = 2. Triplet (2, 3, 5) is another pit with depth 3.
     Triplet (5, 7, 8) is yet another pit with depth 4.
     There is no pit in this array deeper (i.e. having depth greater) than 4.

     Write a function:

     class Solution { public int solution(int[] A); }
     that, given a non-empty zero-indexed array A consisting of N integers, returns the depth of the deepest pit
     in array A. The function should return −1 if there are no pits in array A.

     For example, consider array A consisting of 10 elements such that:

     A[0] =  0
     A[1] =  1
     A[2] =  3
     A[3] = -2
     A[4] =  0
     A[5] =  1
     A[6] =  0
     A[7] = -3
     A[8] =  2
     A[9] =  3
     the function should return 4, as explained above.

     Assume that:

     N is an integer within the range [1..1,000,000];
     each element of array A is an integer within the range [−100,000,000..100,000,000].
     Complexity:

     expected worst-case time complexity is O(N);
     expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
     Elements of input arrays can be modified.

    Implementation:
     - found next top point a (next point is decreased)
     - found next most bottom point b (such as all items between a and b are decreasing
     and next point after b is increasing)
     - found next top point c (such as all items between b and c are increasing
     and next point after c is decreasing)
*/


    public int solution(int[] a) {
        int maxDepth = -1;
        if (a.length >= 3) {
            int a1, a2, a3;
            int i = 1;
            while (i < a.length) {
                // go to the next top point
                while (i < a.length && a[i] > a[i - 1]) i++;
                a1 = i - 1;
                // go to the next bottom point
                while (i < a.length && a[i] < a[i - 1]) i++;
                a2 = i - 1;
                // go to the next top point
                while (i < a.length && a[i] > a[i - 1]) i++;
                a3 = i - 1;
                if (a1 < a2 && a2 < a3) {
                    maxDepth = (int) Math.max(new Long(maxDepth), Math.min(new Long(a[a1]) - new Long(a[a2]), new Long(a[a3]) - new Long(a[a2])));
                }
            }
        }
        return maxDepth;
    }
}
