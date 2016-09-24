package algorithms.tasks.hedgehog;

/**
 * Imagine the rectangular garden sized M x N. Garden consists of square zones with one apple-tree in each zone.
 * There can be several apples under each tree.
 * There is an hedgehog in upper left square of the garden. It moves to lower right corner with some restrictions:
 * the hedgehog  can  only  move to the next right or to the next lower square.
 * <p>
 * Please, make an application to count the max amount of apples the hedgehog can collect on its way.
 */

public interface Solution {
    int getMaxApples(Garden garden, Animal animal);
}
