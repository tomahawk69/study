package algorithms.tasks.hedgehog;

/**
 * Garden is a graph in fact. For now it may be used by hedgehog with invariant right-down movement,
 * however it may be used by other animals with different moving pattern
 */
public class Garden {
    private int[] fields;
    private final int maxX;
    private final int maxY;

    public Garden(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.fields = new int[maxX * maxY];
    }

    public int flattenCoords(int x, int y) {
        return x * maxX + y;
    }

    public int flattenCoords(Point point) {
        return flattenCoords(point.getX(), point.getY());
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isBound(Point p) {
        return (p.getX() >= 0 && p.getX() < maxX && p.getY() >= 0 && p.getY() < maxY);
    }

    public int getApplesCount(Point p) {
        return fields[flattenCoords(p)];
    }

    public void setApplesCount(int x, int y, int applesCount) {
        fields[flattenCoords(x, y)] = applesCount;
    }

    @Override
    public String toString() {
        StringBuilder fieldsString = new StringBuilder();
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                fieldsString.append(fields[flattenCoords(i, j)] + "\t");
            }
            fieldsString.append("\n");
        }
        return "Garden{\n" +
                fieldsString.toString() +
                "maxX=" + maxX +
                ", maxY=" + maxY +
                '}';
    }
}
