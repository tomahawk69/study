package algorithms.tasks.test;

public class MyFindArray {

    public int findArray(int[] array, int[] subArray) {
        // null (array || subArray) => NPE
        // subArray == empty => -1
        // array == empty => -1
        if (subArray.length == 0) {
            return -1;
        }
        int i = 0;
        int result = -1;
        int end = array.length - subArray.length;
        int start = subArray[0];
        while (i < end) {
            while (array[i] != start && i < end) {
                i++;
            }
            if (array[i] != start) {
                break;
            }
            boolean found = true;
            for (int j = 1; j < subArray.length; j++) {
                if (subArray[j] != array[i + j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                result = i;
            }
            i++;
        }
        return result;
    }
}
