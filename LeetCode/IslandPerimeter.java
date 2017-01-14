// https://leetcode.com/contest/leetcode-weekly-contest-10/problems/island-perimeter/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class IslandPerimeter {

    public static void main(String[] args){
        System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{{0,1,0,0}, {1,1,1,0}, {0,1,0,0}, {1,1,0,0}}));
        System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{{1,1}, {1,1}}));
    }

    // bfs
    // pos = row * NUM_COLS + col
    // find an island: += 4, has a neighbor: -= 1
    public int islandPerimeter(int[][] grid) {
        int NUM_ROWS = grid.length;
        int NUM_COLS = grid[0].length;

        int start = 0;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (grid[row][col] == 1) {
                    start = row * NUM_COLS + col;
                    break;
                }
            }
        }

        int result = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        Queue<Integer> auxQueue = new LinkedList<>();
        Set<Integer> used = new HashSet<>();
        used.add(start);
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                int curr = queue.remove();
                result += 4;

                int row = curr / NUM_COLS;
                int col = curr % NUM_COLS;
                for (int rowInc = -1; rowInc <= 1; rowInc++) {
                    for (int colInc = -1; colInc <= 1; colInc++) {
                        if (Math.abs(rowInc) == Math.abs(colInc)) {
                            continue;
                        }

                        int nextRow = row + rowInc;
                        if (nextRow < 0 || nextRow >= NUM_ROWS) {
                            continue;
                        }

                        int nextCol = col + colInc;
                        if (nextCol < 0 || nextCol >= NUM_COLS) {
                            continue;
                        }

                        int next = nextRow * NUM_COLS + nextCol;
                        if (grid[nextRow][nextCol] == 1) {
                            result--;
                            if (!used.contains(next)) {
                                auxQueue.add(next);
                                used.add(next);
                            }
                        }
                    }
                }
            }

            Queue<Integer> tmpQueue = queue;
            queue = auxQueue;
            auxQueue = tmpQueue;
        }

        return result;
    }

}
