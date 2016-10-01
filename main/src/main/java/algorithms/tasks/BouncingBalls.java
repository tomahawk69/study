package algorithms.tasks;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * TODO: Bouncing balls
 */
public class BouncingBalls {

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        Ball[] balls = IntStream.range(0, size).mapToObj(i -> new Ball()).toArray(Ball[]::new);
        while (true) {
            StdDraw.clear();
            Arrays.stream(balls).forEach(b -> {
               b.move(0.5);
                b.draw();
            });
        }
    }

    private static class Ball {
        private double rx, ry;
        private double vx, vy;
        private double radius;

        public Ball() {
        }


        public void draw() {
            StdDraw.filledCircle(rx, ry, radius);

        }

        public void move(double dt) {
            if ((rx + vx * dt < radius)  || (rx + vx * dt > 1.0 - radius)) {
                vx = -vx;
            }
            if ((ry + vy * dt < radius)  || (ry + vy * dt > 1.0 - radius)) {
                vy = -vy;
            }
            rx += vx * dt;
            ry += vy * dt;
        }
    }
}
