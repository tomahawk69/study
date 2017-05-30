package algorithms.tasks;

import java.util.Arrays;

/**
 * TODO finish Equilibrium index
 * Equilibrium index of an array is an index such that the sum of elements at lower indexes
 * is equal to the sum of elements at higher indexes. For example, in an array A:
 * <p>
 * A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0
 * <p>
 * 3 is an equilibrium index, because:
 * A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 * <p>
 * 6 is also an equilibrium index, because sum of zero elements is zero, i.e., A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0
 * <p>
 * 7 is not an equilibrium index, because it is not a valid index of array A.
 * <p>
 * Write a function int equilibrium(int[] arr, int n); that given a sequence arr[] of size n,
 * returns an equilibrium index (if any) or -1 if no equilibrium indexes exist.
 */
public class EquilibriumIndex {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length > 0) {
            long suml = 0;
            long sumr = Arrays.stream(A, 1, A.length).mapToLong(Long::new).sum();
            System.out.println("sunr = " + sumr);
            if (sumr == 0) return 0;
            System.out.println("starting");
            for (int i = 1; i < A.length; i++) {
                suml += A[i - 1];
                sumr -= A[i];
                System.out.println(i + " = " + suml + " : " + sumr);
                if (suml == sumr) return i;
            }
        }
        return -1;
    }
}
