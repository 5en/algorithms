package jp.co.worksap.global;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Orienteering2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int H = sc.nextInt();
        sc.nextLine();

        Point[][] map = new Point[W][H];
        int SX = -1;
        int SY = -1;
        int GX = -1;
        int GY = -1;
        Set<Point> CPS = new HashSet<>();
        for (int y = 0; y < H; y++) {
            char[] points = sc.nextLine().toCharArray();
            for (int x = 0; x < W; x++) {
                char symbol = points[x];
                map[x][y] = new Point(symbol, x, y);
                switch (symbol) {
                    case 'S':
                        SX = x;
                        SY = y;
                        break;
                    case 'G':
                        GX = x;
                        GY = y;
                        break;
                    case '@':
                        CPS.add(map[x][y]);
                        break;
                    default:
                        break;
                }
            }
        }

        System.out.println(new Problem(W, H, map, SX, SY, GX, GY, CPS).solve());

        sc.close();
    }

    private static final class Point {
        private final char symbol;
        private final int x;
        private final int y;
        private final Map<Set<Point>, Integer> distMap; // map from set of passed checkpoints to min distance from S

        public Point(char symbol, int x, int y) {
            this.symbol = symbol;
            this.x = x;
            this.y = y;

            if (this.symbol == '#') {
                this.distMap = null;
            } else {
                this.distMap = new HashMap<Set<Point>, Integer>();
                switch (this.symbol) {
                    case 'S':
                        this.distMap.put(new HashSet<Point>(), 0);
                        break;
                    case '.':
                    case 'G':
                        this.distMap.put(new HashSet<Point>(), Integer.MAX_VALUE);
                        break;
                    case '@':
                        Set<Point> cps = new HashSet<Point>();
                        cps.add(this);
                        this.distMap.put(cps, Integer.MAX_VALUE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static final class Problem {
        private final int W;
        private final int H;
        private final Point[][] map;
        private final int SX; // x coordinate of S
        private final int SY; // y coordinate of S
        private final int GX; // x coordinate of G
        private final int GY; // y coordinate of G
        private final Set<Point> CPS; // set of checkpoints

        public Problem(int W, int H, Point[][] map, int SX, int SY, int GX, int GY, Set<Point> CPS) {
            this.W = W;
            this.H = H;
            this.map = map;
            this.SX = SX;
            this.SY = SY;
            this.GX = GX;
            this.GY = GY;
            this.CPS = CPS;
        }

        public int solve() {
            Point S = map[SX][SY];
            Point G = map[GX][GY];

            // initialization
            Queue<Point> queue = new LinkedList<Point>();
            queue.add(S);

            // BFS
            Queue<Point> newQueue = new LinkedList<Point>();
            while (!queue.isEmpty()) {
                Point curr = queue.remove(); // current point

                if (curr == G && G.distMap.containsKey(CPS)) {
                    // succeed!
                    return G.distMap.get(CPS);
                }

                for (int x = curr.x - 1; x <= curr.x + 1; x++) {
                    for (int y = curr.y - 1; y <= curr.y + 1; y++) {
                        if (Math.abs(x - curr.x) + Math.abs(y - curr.y) != 1) {
                            continue;
                        }

                        // check adjacent points

                        if (x < 0 || x >= W || y < 0 || y >= H) {
                            // outside
                            continue;
                        }

                        Point adj = map[x][y]; // adjacent point of curr
                        if (adj.symbol == '#') {
                            // block
                            continue;
                        }

                        boolean hasUpdate = false;

                        for (Map.Entry<Set<Point>, Integer> entry : curr.distMap.entrySet()) {
                            Set<Point> cps = entry.getKey();
                            Integer dist = entry.getValue();

                            if (!adj.distMap.containsKey(cps) || (adj.distMap.containsKey(cps) && dist + 1 < adj.distMap.get(cps))) {
                                // update
                                adj.distMap.put(cps, dist + 1);
                                hasUpdate = true;
                            }

                            if (adj.symbol == '@') {
                                cps = new HashSet<Point>(cps);
                                cps.add(adj);

                                if (!adj.distMap.containsKey(cps) || (adj.distMap.containsKey(cps) && dist + 1 < adj.distMap.get(cps))) {
                                    // update
                                    adj.distMap.put(cps, dist + 1);
                                    hasUpdate = true;
                                }
                            }
                        }

                        if (hasUpdate) {
                            newQueue.add(adj);
                        }
                    }
                }

                // next round of search
                if (queue.isEmpty()) {
                    queue = newQueue;
                }
            }

            return -1;
        }
    }
}
