package jp.co.worksap.global;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Orienteering {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int W = sc.nextInt();
        int H = sc.nextInt();
        sc.nextLine();

        Point[][] map = new Point[W][H];
        Point S = null;
        Point G = null;
        Set<Point> IPS = new HashSet<Point>(); // important points
        for (int y = 0; y < H; y++) {
            char[] points = sc.nextLine().toCharArray();
            for (int x = 0; x < W; x++) {
                char symbol = points[x];
                switch (symbol) {
                    case '#':
                        map[x][y] = new Point(PointType.BLOCK, x, y);
                        break;
                    case '.':
                        map[x][y] = new Point(PointType.PASS, x, y);
                        break;
                    case '@':
                        map[x][y] = new Point(PointType.CHECKPOINT, x, y);
                        IPS.add(map[x][y]);
                        break;
                    case 'S':
                        map[x][y] = new Point(PointType.START, x, y);
                        if (S != null) {
                            System.out.println(-1);
                            return;
                        }
                        S = map[x][y];
                        IPS.add(map[x][y]);
                        break;
                    case 'G':
                        map[x][y] = new Point(PointType.GOAL, x, y);
                        if (G != null) {
                            System.out.println(-1);
                            return;
                        }
                        G = map[x][y];
                        IPS.add(map[x][y]);
                        break;
                    default:
                        System.out.println(-1);
                        return;
                }
            }
        }

        System.out.println(new Problem(W, H, map, S, G, IPS).solve());

        sc.close();
    }

    private static enum PointType {
        BLOCK, PASS, CHECKPOINT, START, GOAL;
    }

    private static final class Point {
        private final PointType type;
        private final int x;
        private final int y;
        private final Map<Point, Integer> minDistMap; // all-pair shortest distance between important points
        private int tmpDist; // for preprocess only

        public Point(PointType type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;

            if (this.type == PointType.BLOCK || this.type == PointType.PASS) {
                this.minDistMap = null;
            } else {
                this.minDistMap = new HashMap<Point, Integer>();
            }
        }
    }

    private static final class Problem {
        private final int W;
        private final int H;
        private final Point[][] map;
        private final Point S;
        private final Point G;
        private final Set<Point> IPS;

        public Problem(int W, int H, Point[][] map, Point S, Point G, Set<Point> IPS) {
            this.W = W;
            this.H = H;
            this.map = map;
            this.S = S;
            this.G = G;
            this.IPS = IPS;
        }

        public final int solve() {
            preprocess();

            if (IPS.size() == 2) {
                // no checkpoint
                return S.minDistMap.get(G);
            } else if (!satisfy()) {
                return -1;
            } else {
                return findMinDist();
            }
        }

        /**
         * Find all-pair shortest paths among important points
         */
        private void preprocess() {
            for (Point from : IPS) {
                // min heap
                PriorityQueue<Point> pq = new PriorityQueue<Point>(W * H, new Comparator<Point>() {
                    @Override
                    public int compare(Point p1, Point p2) {
                        if (p1.tmpDist == p2.tmpDist) {
                            return 0;
                        } else if (p1.tmpDist == -1) {
                            return 1;
                        } else if (p2.tmpDist == -1) {
                            return -1;
                        } else {
                            return p1.tmpDist < p2.tmpDist ? -1 : 1;
                        }
                    }
                });

                // processed set
                Set<Point> ps = new HashSet<Point>();

                // init, set dist of Node "from" to 0, set dists of other nodes to -1
                for (int x = 0; x < W; x++) {
                    for (int y = 0; y < H; y++) {
                        Point p = map[x][y];

                        if (p.type == PointType.BLOCK) {
                            continue;
                        }

                        if (p == from) {
                            p.tmpDist = 0;
                        } else {
                            p.tmpDist = -1;
                        }

                        pq.add(p);
                    }
                }

                while (!pq.isEmpty()) {
                    Point curr = pq.remove();

                    if (curr.tmpDist == -1) {
                        break;
                    }

                    // check adjacent points
                    for (int x = curr.x - 1; x <= curr.x + 1; x++) {
                        for (int y = curr.y - 1; y <= curr.y + 1; y++) {
                            if (Math.abs(x - curr.x) + Math.abs(y - curr.y) != 1) {
                                continue;
                            }

                            if (x < 0 || x >= W || y < 0 || y >= H) {
                                // outside
                                continue;
                            }

                            Point adj = map[x][y]; // adjacent point of curr
                            if (adj.type == PointType.BLOCK) {
                                // block
                                continue;
                            }

                            if (pq.contains(adj) && (adj.tmpDist == -1 || curr.tmpDist + 1 < adj.tmpDist)) {
                                adj.tmpDist = curr.tmpDist + 1;
                                pq.remove(adj);
                                pq.add(adj);
                            }

                            ps.add(curr);
                        }
                    }

                    if (ps.containsAll(IPS)) {
                        // all important points have been processed
                        break;
                    }
                }

                for (Point to : IPS) {
                    from.minDistMap.put(to, to.tmpDist);
                }
            }
        }

        private boolean satisfy() {
            for (Point ip : IPS) {
                if (S.minDistMap.get(ip) == -1) {
                    return false;
                }
            }

            return true;
        }

        private int findMinDist() {
            Map<SubProblem, Integer> memo = new HashMap<SubProblem, Integer>();
            return solveSub(memo, new SubProblem(IPS, G));
        }

        private int solveSub(Map<SubProblem, Integer> memo, SubProblem sub) {
            if (memo.containsKey(sub)) {
                return memo.get(sub);
            }

            // base case
            if (sub.ips.size() == 2) {
                int minDist = S.minDistMap.get(sub.g);
                memo.put(sub, minDist);
                return minDist;
            }

            int minDist = -1;
            for (Point ip : sub.ips) {
                // choose ip as the intermediate point
                // S -> ip -> g

                if (ip.equals(S) || ip.equals(sub.g)) {
                    continue;
                }

                Set<Point> ips = new HashSet<Point>(sub.ips);
                ips.remove(sub.g);
                int dist1 = solveSub(memo, new SubProblem(ips, ip));
                int dist2 = ip.minDistMap.get(sub.g);
                if (dist1 != -1 && dist2 != -1) {
                    if (minDist == -1 || dist1 + dist2 < minDist) {
                        minDist = dist1 + dist2;
                    }
                }
            }

            memo.put(sub, minDist);
            return minDist;
        }
    }

    // ips: set of important points containing S and g, S != g
    // g: destination
    // L(ips, g) = shortest path from s to g that passes each point in ips exactly once.
    // L(ips, g) = min {L(ips-g, t) + dist(t,g)} for every t in ips such that t != g
    // O(N*2^N)
    private static final class SubProblem {
        private final Set<Point> ips;
        private final Point g;

        public SubProblem(Set<Point> ips, Point g) {
            this.ips = ips;
            this.g = g;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null) {
                return false;
            }

            if (this.getClass() != o.getClass()) {
                return false;
            }

            SubProblem sub = (SubProblem) o;

            return this.ips.equals(sub.ips) && this.g.equals(sub.g);
        }

        @Override
        public int hashCode() {
            return ips.hashCode() + g.hashCode();
        }
    }
}
