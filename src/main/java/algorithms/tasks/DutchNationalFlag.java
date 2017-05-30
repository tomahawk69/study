package algorithms.tasks;

/**
 * TODO: Dutch national flag
 * Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 * - swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * - color(i): color of pebble in bucket i.
 *
 * The performance requirements are as follows:
 * - At most n calls to color().
 * - At most n calls to swap().
 * - Constant extra space.
 */
public class DutchNationalFlag {
    private final int[] items;

    public DutchNationalFlag(int[] items) {
        this.items = items;
    }

    public void sort() {
        int i = 0;
        int j = 0;
        int k = items.length - 1;
        while (j < k) {
            Color color = getColor(j);
            if (color == Color.RED) {
                swap(i++, j++);
            } else if (color == Color.BLUE) {
                swap(j, k--);
            } else {
                j++;
            }
        }
    }

    public void swap(int i, int j) {
        int k = items[i];
        items[i] = items[j];
        items[j] = k;
    }

    public Color getColor(int i) {
        return Color.valueOfIndex(items[i]);
    }

    public enum Color {
        RED, WHITE, BLUE;

        public static Color valueOfIndex(int index) {
            if (index == 0) {
                return RED;
            } else if (index == 1) {
                return WHITE;
            } else if (index == 2) {
                return BLUE;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
