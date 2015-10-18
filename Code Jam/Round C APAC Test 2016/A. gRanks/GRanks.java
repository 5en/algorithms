import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GRanks {
    public static void main(String[] args) throws IOException {
        //        Scanner sc = new Scanner(System.in);
        //        Scanner sc = new Scanner(new File("A-small.in.txt"));
        Scanner sc = new Scanner(new File("A-large.in.txt"));
        //        PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("A-output.txt");

        int T = sc.nextInt();
        sc.nextLine();
        for (int t = 1; t <= T; t++) {
            out.printf("Case #%d:\n", t);
            process(sc, out);
        }

        out.flush();
        out.close();
    }

    private static void process(Scanner sc, PrintWriter out) {
        int P = sc.nextInt();
        int[] S = new int[P];
        for (int i = 0; i < P; i++) {
            S[i] = sc.nextInt();
        }
        int N = sc.nextInt();

        Map<String, Athlete> map = new HashMap<String, Athlete>();
        for (int n = 0; n < N; n++) {
            int W = sc.nextInt();
            String line = sc.nextLine().trim();
            String[] names = line.split(" ");
            for (int order = 0; order < P; order++) {
                String name = names[order];
                if (!map.containsKey(name)) {
                    map.put(name, new Athlete(name));
                }
                map.get(name).scores.add(W * S[order]);
            }
        }

        int M = sc.nextInt();

        List<Athlete> athletes = new ArrayList<Athlete>(map.values());
        for (Athlete athlete : athletes) {
            athlete.calScore(M);
        }

        Collections.sort(athletes, new Comparator<Athlete>() {
            @Override
            public int compare(Athlete o1, Athlete o2) {
                return o1.score > o2.score ? -1 : (o1.score < o2.score ? 1 : o1.name
                    .compareTo(o2.name));
            }
        });

        int prevOrder = -1;
        int prevScore = -1;
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            int order = athlete.score == prevScore ? prevOrder : i + 1;
            out.printf("%d: %s\n", order, athlete.name);
            prevOrder = order;
            prevScore = athlete.score;
        }
    }

    private static class Athlete {
        public final String                 name;
        public final PriorityQueue<Integer> scores;
        public int                          score = 0;

        public Athlete(String name) {
            this.name = name;
            this.scores = new PriorityQueue<Integer>(Collections.reverseOrder());
        }

        public void calScore(int M) {
            for (int i = 0; i < M; i++) {
                if (scores.isEmpty()) {
                    break;
                }
                score += scores.remove();
            }
        }
    }
}
