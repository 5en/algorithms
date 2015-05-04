public class Q1_3_PrefixReversal {
    public static void main(String[] args) {
        new Problem(new int[]{3, 1, 2}).run();
    }

    private static class Problem {
        private int minR; // min # of reversals
        private int[] posR; // position of reversals
        private int[] cakes;
        private int[] currPosR;

        private int numSearch; // stat only

        public Problem(int[] cakes) {
            this.cakes = cakes.clone();
            int ub = upperBound();
            this.minR = ub;
            this.posR = new int[ub];
            this.currPosR = new int[ub + 1];

            numSearch = 0;
        }

        public void run() {
            search(0);
            output();
        }

        // step is the # of reversals already taken
        private void search(int step) {
            numSearch++;

            if (step + lowerbound() > minR) {
                // exit case 1
                return;
            }

            if (isSorted()) {
                // exit case 2
                if (step < minR) {
                    // obtain better solution, update
                    minR = step;
                    for (int i = 0; i < minR; i++) {
                        posR[i] = currPosR[i];
                    }
                }
                return;
            }

            for (int i = 1; i < cakes.length; i++) {
                reverse(i); // reverse cakes[0...i]
                currPosR[step] = i;

                search(step + 1);

                reverse(i); // back-track
            }
        }

        // maximum # of reversals
        // 2 reversals can make the largest-radius cake at the bottom
        private int upperBound() {
            return 2 * (cakes.length - 1);
        }

        // minimum # of reversals
        // (each flip can at most let one pair of radius-adjacent cakes to be physically adjacent)
        private int lowerbound() {
            int count = 0;
            for (int i = 1; i < cakes.length; i++) {
                if (Math.abs(cakes[i] - cakes[i - 1]) != 1) {
                    // if adjacent cakes do not have adjacent radiuses
                    count++;
                }
            }

            return count;
        }

        private boolean isSorted() {
            for (int i = 1; i < cakes.length; i++) {
                if (cakes[i] < cakes[i - 1]) {
                    return false;
                }
            }

            return true;
        }

        // reverse cases[0,1,...,pos]
        private void reverse(int pos) {
            for (int i = 0, j = pos; i < j; i++, j--) {
                int tmp = cakes[i];
                cakes[i] = cakes[j];
                cakes[j] = tmp;
            }
        }

        private void output() {
            System.out.println("Number of reversals = " + minR);
            for (int i = 0; i < minR; i++) {
                System.out.println(posR[i]);
            }
            System.out.println("Number of searches = " + numSearch);
        }
    }
}
