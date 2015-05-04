import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ItzChess {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("D-small.in.txt"));
        Scanner sc = new Scanner(new File("D-large.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        sc.nextLine();
        for (int t=1; t<=T; t++) {
            int N = sc.nextInt();
            sc.nextLine();
            
            Piece[][] m = new Piece[9][9];
            List<Piece> pieces = new LinkedList<Piece>();
            for (int n=0; n<N; n++) {
                String pieceInfo = sc.nextLine();
                int row = Integer.parseInt(pieceInfo.substring(1,2));
                int col = 8 - (pieceInfo.charAt(0)-'A');
                char type = pieceInfo.charAt(3);
                
                Piece piece = new Piece(type, row, col);
                pieces.add(piece);
                m[row][col] = piece;
            }
            
            out.printf("Case #%d: %d\n", t, process(m, pieces));
        }
        
        out.flush();
        out.close();
    }
    
    private static int process(Piece[][] m, List<Piece> pieces) {
        int count = 0;
        
        for (Piece from : pieces) {
            switch (from.type) {
                case 'K':
                    for (int row=from.row-1; row<=from.row+1; row++) {
                        for (int col=from.col-1; col<=from.col+1; col++) {
                            if (row==from.row && col==from.col) {
                                continue;
                            }
                            
                            if (isValidPos(row, col) && m[row][col] != null) {
                                count++;
                            }
                        }
                    }
                    break;
                case 'P':
                    int col = from.col - 1;
                    int row = from.row - 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row + 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    break;
                case 'Q':
                    // diagonal
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    // straight
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    break;
                case 'R':
                    // straight
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    break;
                case 'B':
                 // diagonal
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row++;
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        col++;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    row = from.row;
                    col = from.col;
                    while (true) {
                        row--;
                        col--;
                        if (!isValidPos(row, col)) {
                            break;
                        }
                        if (m[row][col] != null) {
                            count++;
                            break;
                        }
                    }
                    break;
                case 'N':
                    row = from.row + 1;
                    col = from.col + 2;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row + 1;
                    col = from.col - 2;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row - 1;
                    col = from.col + 2;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row - 1;
                    col = from.col - 2;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row + 2;
                    col = from.col + 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row + 2;
                    col = from.col - 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row - 2;
                    col = from.col + 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    row = from.row - 2;
                    col = from.col - 1;
                    if (isValidPos(row, col) && m[row][col] != null) {
                        count++;
                    }
                    break;
            }
        }
        
        return count;
    }
    
    private static boolean isValidPos(int row, int col) {
        if (row < 1 || row > 8 || col < 1 || col > 8) {
            return false;
        }
        
        return true;
    }
    
    private static class Piece {
        public final char type;
        public final int row;
        public final int col;
        
        public Piece(char type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }
    }
}
