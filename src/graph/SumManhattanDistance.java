package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SumManhattanDistance {
    public static void main(String[] args) {
        int[] x = { 0, 1, 2, 3 };
        int[] y = { 0, 1, 3, 2 };
        int[] dists = sumDist(x, y);
        System.out.println(Arrays.toString(dists));
    }

    // O(N*logN)
    // for each point, calculate the sum of Manhattan distance to other points
    public static int[] sumDist(int[] x, int[] y) {
        int N = x.length;
        int[] dists = new int[N];

        List<Point> points = new ArrayList<Point>(N);
        for (int i = 0; i < N; i++) {
            points.add(new Point(i, x[i], y[i]));
        }

        // sort on x
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.x < p2.x ? -1 : (p1.x == p2.x ? 0 : 1);
            }
        });
        int prevSumDist = 0;
        for (int i = 1; i < N; i++) {
            prevSumDist += i * points.get(i).distX(points.get(i - 1));
            dists[points.get(i).index] += prevSumDist;
        }
        prevSumDist = 0;
        for (int i = N - 2; i >= 0; i--) {
            prevSumDist += (N - i - 1) * points.get(i).distX(points.get(i + 1));
            dists[points.get(i).index] += prevSumDist;
        }

        // sort on y
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.y < p2.y ? -1 : (p1.y == p2.y ? 0 : 1);
            }
        });
        prevSumDist = 0;
        for (int i = 1; i < N; i++) {
            prevSumDist += i * points.get(i).distY(points.get(i - 1));
            dists[points.get(i).index] += prevSumDist;
        }
        prevSumDist = 0;
        for (int i = N - 2; i >= 0; i--) {
            prevSumDist += (N - i - 1) * points.get(i).distY(points.get(i + 1));
            dists[points.get(i).index] += prevSumDist;
        }

        return dists;
    }

    private static class Point {
        public final int index;
        public final int x;
        public final int y;

        public Point(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        public int distX(Point p) {
            return Math.abs(x - p.x);
        }

        public int distY(Point p) {
            return Math.abs(y - p.y);
        }
    }
}
