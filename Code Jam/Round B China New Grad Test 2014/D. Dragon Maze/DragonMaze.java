import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DragonMaze {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("D-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("D-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");

        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int en_x = sc.nextInt();
            int en_y = sc.nextInt();
            int ex_x = sc.nextInt();
            int ex_y = sc.nextInt();
            Pos en = new Pos(en_x, en_y);
            Pos ex = new Pos(ex_x, ex_y);
            Cell[][] maze = new Cell[N][M];
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    long power = sc.nextLong();
                    maze[n][m] = new Cell(n, m, power);
                }
            }

            out.printf("Case #%d: %s\n", t, process(N, M, maze, en, ex));
        }

        out.flush();
        out.close();
        
        sc.close();
    }

    private static String process(int N, int M, Cell[][] maze, Pos en, Pos ex) {
        boolean succeed = false;

        Queue<Cell> mainQueue = new LinkedList<Cell>();
        Queue<Cell> auxQueue = new LinkedList<Cell>();

        // init
        maze[en.x][en.y].opt_steps = 0;
        mainQueue.add(maze[en.x][en.y]);

        while (!mainQueue.isEmpty()) {
            Cell curCell = mainQueue.remove();

            if (curCell.x == ex.x && curCell.y == ex.y) {
                succeed = true;
                break;
            }

            // adjacent Cells
            for (int x_inc = -1; x_inc <= 1; x_inc++) {
                for (int y_inc = -1; y_inc <= 1; y_inc++) {
                    if (x_inc != 0 && y_inc != 0) {
                        continue;
                    }

                    if (x_inc == 0 && y_inc == 0) {
                        continue;
                    }

                    if (curCell.x + x_inc < 0 || curCell.x + x_inc >= N || curCell.y + y_inc < 0 || curCell.y + y_inc >= M) {
                        continue;
                    }

                    Cell adjCell = maze[curCell.x + x_inc][curCell.y + y_inc];
                    if (adjCell.power == -1) {
                        continue;
                    }

                    if (curCell.opt_steps + 1 < adjCell.opt_steps) {
                        adjCell.opt_steps = curCell.opt_steps + 1;
                        adjCell.opt_totalPower = curCell.opt_totalPower + adjCell.power;
                        auxQueue.add(adjCell);
                    } else if (curCell.opt_steps + 1 == adjCell.opt_steps && curCell.opt_totalPower + adjCell.power > adjCell.opt_totalPower) {
                        adjCell.opt_totalPower = curCell.opt_totalPower + adjCell.power;
                        auxQueue.add(adjCell);
                    }
                }
            }

            if (mainQueue.isEmpty()) {
                mainQueue = auxQueue;
            }
        }

        if (!succeed) {
            return "Mission Impossible.";
        } else {
            return String.valueOf(maze[ex.x][ex.y].opt_totalPower);
        }
    }

    private static class Cell {
        public final int x;
        public final int y;
        public final long power;

        public int opt_steps = Integer.MAX_VALUE;
        public long opt_totalPower;

        public Cell(int x, int y, long power) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.opt_totalPower = power;
        }
    }

    private static class Pos {
        public final int x;
        public final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
