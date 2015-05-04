import java.util.HashSet;
import java.util.Set;

public class Q1_15_Sudoku {
    public static void main(String[] args) {
        Cell[][] cells = genSudoku();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                sb.append(cells[i][j].value).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length()).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static Cell[][] genSudoku() {
        Cell[][] cells = new Cell[9][9];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        Pos pos = new Pos();
        while (true) {
            Cell cell = cells[pos.row][pos.col];

            if (cell.value == 0) {
                setCandidates(cells, pos);
            }

            if (cell.candidates.isEmpty()) {
                if (pos.row == 0 && pos.col == 0) {
                    return null; // reach the beginning, fail;
                }
                // back track
                cell.clear();
                pos.prev();
            } else {
                cell.nextValidValue();
                if (pos.row == cells.length - 1 && pos.col == cells[0].length - 1) {
                    return cells; // reach the end, succeed;
                }
                pos.next(); // try next
            }
        }
    }

    private static void setCandidates(Cell[][] cells, Pos pos) {
        Cell cell = cells[pos.row][pos.col];
        cell.candidates.addAll(Cell.VALUES);

        for (int row = 0; row < cells.length; row++) {
            if (row == pos.row) {
                continue;
            }

            Cell tmpCell = cells[row][pos.col];
            if (tmpCell.value != 0) {
                cell.candidates.remove(tmpCell.value);
            }
        }

        for (int col = 0; col < cells[0].length; col++) {
            if (col == pos.col) {
                continue;
            }

            Cell tmpCell = cells[pos.row][col];
            if (tmpCell.value != 0) {
                cell.candidates.remove(tmpCell.value);
            }
        }

        for (int row = (pos.row - (pos.row % 3)); row < (pos.row - (pos.row % 3) + 3); row++) {
            for (int col = (pos.col - (pos.col % 3)); col < (pos.col - (pos.col % 3) + 3); col++) {
                if (row == pos.row && col == pos.col) {
                    continue;
                }

                Cell tmpCell = cells[row][col];
                if (tmpCell.value != 0) {
                    cell.candidates.remove(tmpCell.value);
                }
            }
        }
    }

    private static class Cell {
        public static Set<Integer> VALUES = new HashSet<Integer>(9);
        static {
            for (int v = 1; v <= 9; v++) {
                VALUES.add(v);
            }
        }

        public Set<Integer> candidates = new HashSet<Integer>();
        public int value; // 1 ~ 9, 0 means unprocessed

        public void nextValidValue() {
            value = candidates.iterator().next();
            candidates.remove(value);
        }

        public void clear() {
            candidates.clear();
            value = 0;
        }
    }

    private static class Pos {
        public int row = 0; // 0 ~ 8
        public int col = 0; // 0 ~ 8

        public void next() {
            row += (col + 1) / 9;
            col = (col + 1) % 9;
        }

        public void prev() {
            if (col == 0) {
                row--;
                col = 8;
            } else {
                col--;
            }
        }
    }
}
