package algorithms.tasks;

public class NearestMatrix {

    public int[] matrix(int length) {
        double s = Math.sqrt(length);
        int floor = (int) Math.floor(s);
        int ceil = (int) Math.ceil(s);
        int min = Integer.MAX_VALUE;
        int[] result = new int[2];
        for (int i = floor; i <= ceil; i++) {
            for (int j = i; j <= ceil; j++) {
                if (i * j >= length && i * j < min) {
                    result[0] = Math.min(i, j);
                    result[1] = Math.max(i, j);
                    min = i * j;
                    if (min == length) {
                        return result;
                    }
                }
            }
        }
        return result;
    }
}
