// Given a zero-filled matrix, randomly find a path from top-left corner to bottom-right corner
// The paths should cover any possibility

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPath {

    public static void main(String[] args) {
        int[][] m = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        findPath(m);
        print(m);
    }

    public static void findPath(int[][] m) {
        findPathSub(m, 0, 0, 1);
    }

    private static boolean findPathSub(int[][] m, int curX, int curY, int currNo) {
        if (curX < 0 || curX >= m.length || curY < 0 || curY >= m[0].length) {
            return false;
        }

        if (m[curX][curY] > 0) {
            return false;
        }

        m[curX][curY] = currNo;

        if (curX == m.length - 1 && curY == m[0].length - 1) {
            return true;
        }

        List<Integer> dxys = new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 2, 3 }));
        while (!dxys.isEmpty()) {
            // randomly choose one
            Random rand = new Random();
            int index = Math.abs(rand.nextInt()) % dxys.size();
            int dx = 0;
            int dy = 0;
            switch (dxys.remove(index)) {
                case 0:
                    dx = 0;
                    dy = -1;
                    break;
                case 1:
                    dx = 0;
                    dy = 1;
                    break;
                case 2:
                    dx = -1;
                    dy = 0;
                    break;
                case 3:
                    dx = 1;
                    dy = 0;
                    break;
            }

            if (findPathSub(m, curX + dx, curY + dy, currNo + 1)) {
                return true;
            }
        }

        m[curX][curY] = 0;

        return false;
    }

    private static void print(int[][] m) {
        for (int[] row : m) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
