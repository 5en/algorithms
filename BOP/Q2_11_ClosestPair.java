import java.util.ArrayList;
import java.util.List;

public class Q2_11_ClosestPair {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(-5, 0));
        points.add(new Point(-1, 1));
        points.add(new Point(-1, 10));
        points.add(new Point(1, 10));
        points.add(new Point(5, 1));
        points.add(new Point(3, 2));
        System.out.println(closestPair(points));
    }

    public static double closestPair(List<Point> points) {
        if (points.size() == 0 || points.size() == 1) {
            return Integer.MAX_VALUE;
        }

        if (points.size() == 2) {
            // sort according to y value
            if (points.get(0).y > points.get(1).y) {
                Point tmp = points.get(0);
                points.set(0, points.get(1));
                points.set(1, tmp);
            }

            return Point.dist(points.get(0), points.get(1));
        }

        // divide
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Point p : points) {
            if (p.x < minX) {
                minX = p.x;
            }
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        int midX = (minX + maxX) / 2;

        List<Point> left = new ArrayList<Point>(); // p.x < midX
        List<Point> right = new ArrayList<Point>(); // p.x > midX
        while (!points.isEmpty()) {
            Point p = points.remove(points.size() - 1); // for efficiency
            if (p.x < midX) {
                left.add(p);
            } else if (p.x > midX) {
                right.add(p);
            } else {
                // p.x == midX
                // balance left and right
                if (left.size() <= right.size()) {
                    left.add(p);
                } else {
                    right.add(p);
                }
            }
        }
        // the points List has been cleared.
        double minDistLeft = closestPair(left);
        double minDistRight = closestPair(right);
        double minDist = (minDistLeft <= minDistRight) ? minDistLeft : minDistRight;

        // conquer
        // sort points in ascending order of its y value
        // pick out points whose x value is within [midX-minDist, midX+minDist]
        List<Point> vPoints = new ArrayList<Point>();
        int li = 0;
        int ri = 0;
        Point tmpP = null;
        while (li < left.size() && ri < right.size()) {
            if (left.get(li).y <= right.get(ri).y) {
                tmpP = left.get(li);
                li++;
            } else {
                tmpP = right.get(ri);
                ri++;
            }
            points.add(tmpP);

            if (tmpP.x >= midX - minDist && tmpP.x <= midX + minDist) {
                vPoints.add(tmpP);
            }
        }
        if (li == left.size()) {
            while (ri < right.size()) {
                tmpP = right.get(ri);
                ri++;
                points.add(tmpP);

                if (tmpP.x >= midX - minDist && tmpP.x <= midX + minDist) {
                    vPoints.add(tmpP);
                }
            }
        } else if (ri == right.size()) {
            while (li < left.size()) {
                tmpP = left.get(li);
                li++;
                points.add(tmpP);

                if (tmpP.x >= midX - minDist && tmpP.x <= midX + minDist) {
                    vPoints.add(tmpP);
                }
            }
        }

        // compute distance between each valid point p and the lowest 7 points whose y value >= p.y  
        for (int i = 0; i < vPoints.size(); i++) {
            for (int j = i + 1; j <= i + 7 && j < vPoints.size(); j++) {
                double tmpDist = Point.dist(vPoints.get(i), vPoints.get(j));
                if (tmpDist < minDist) {
                    minDist = tmpDist;
                }
            }
        }

        return minDist;
    }

    private static class Point {
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static double dist(Point p1, Point p2) {
            return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
        }
    }
}
