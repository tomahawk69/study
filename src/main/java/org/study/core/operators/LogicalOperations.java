package org.study.core.operators;

public class LogicalOperations {
    private int seed;

    private boolean incrementSeed() {
        seed++;
        return true;
    }

    public static void main(String[] args) {
        LogicalOperations obj = new LogicalOperations();
        obj.setSeed(100);
        System.out.println(obj.getSeed() == 100 && obj.getSeed() == 101 | obj.incrementSeed());
        System.out.println(obj.getSeed());
        assert obj.getSeed() == 101;
        System.out.println(obj.getSeed() == 100 | obj.incrementSeed());
        System.out.println(obj.getSeed());
        assert obj.getSeed() == 102;
        System.out.println(obj.getSeed() == 102 || obj.incrementSeed());
        System.out.println(obj.getSeed());
        assert obj.getSeed() == 102;
        System.out.println(obj.getSeed() == 100 && obj.incrementSeed());
        System.out.println(obj.getSeed());
        assert obj.getSeed() == 102;
        System.out.println(obj.getSeed() == 100 & obj.incrementSeed());
        System.out.println(obj.getSeed());
        assert obj.getSeed() == 103;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
