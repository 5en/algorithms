public class Q3_2_TelNum2Word {
    public static void main(String[] args) {
        run("23");
        System.out.println();
        run2("23");
    }

    private static char[][] c = new char[][]{{}, {}, {'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'Q', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y', 'Z'}};

    // iterative
    private static void run(String s) {
        int N = s.length();

        int[] tel = new int[N];
        for (int i = 0; i < N; i++) {
            tel[i] = s.charAt(i) - '0';
        }

        int[] answer = new int[N]; // answer[i] = j: indicates the char c[tel[i]][j]
        while (true) {
            for (int i = 0; i < N; i++) {
                System.out.print(c[tel[i]][answer[i]]);
            }
            System.out.println();

            int i = N - 1;
            while (i >= 0) {
                if (answer[i] < c[tel[i]].length - 1) {
                    answer[i]++;
                    break;
                } else {
                    answer[i] = 0;
                    i--;
                }
            }
            if (i < 0) {
                break;
            }
        }
    }

    // recursive
    private static void run2(String s) {
        int N = s.length();

        int[] tel = new int[N];
        for (int i = 0; i < N; i++) {
            tel[i] = s.charAt(i) - '0';
        }

        int[] answer = new int[N];

        run2SR(tel, answer, 0);
    }

    private static void run2SR(int[] tel, int[] answer, int index) {
        if (index == answer.length) {
            for (int i = 0; i < answer.length; i++) {
                System.out.print(c[tel[i]][answer[i]]);
            }
            System.out.println();
            return;
        }

        for (answer[index] = 0; answer[index] < c[tel[index]].length; answer[index]++) {
            run2SR(tel, answer, index + 1);
        }
    }
}
