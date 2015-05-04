// text T[0...M-1]
// pattern P[0...N-1] (M >= N)

public class KMP {
    public static void main(String[] args) {
        System.out.println(naive("abcdabc", "abc"));
        System.out.println(kmp("abcdabc", "abc"));

        System.out.println(naive("abcdabc", "abd"));
        System.out.println(kmp("abcdabc", "abd"));
    }

    // O((n-m+1)*m)
    public static boolean naive(String T, String P) {
        int M = T.length();
        int N = P.length();

        // check each shift s (0 <= s <= M-N) T[s+0...s+N-1] = P[0...N-1]
        for (int s = 0; s <= M - N; s++) {
            for (int i = 0; i < N; i++) {
                if (T.charAt(s + i) != P.charAt(i)) {
                    break;
                }

                if (i == N - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    // O(M*N)
    // for i = 0,...,M-1, 
    // suppose T[i-q,...,i-1] == P[0...q-1], (q is the length of previously matched)
    // if T[i] == P[q], q++, (if q == N, found!)
    // if T[i] != P[q], q = pre[q], check again
    public static boolean kmp(String T, String P) {
        int M = T.length();
        int N = P.length();

        int[] pre = preprocess(P);

        int q = 0;
        for (int i = 0; i < M; i++) {
            while (q != 0 && T.charAt(i) != P.charAt(q)) {
                q = pre[q];
            }

            if (T.charAt(i) == P.charAt(q)) {
                q++;
            }

            if (q == N) {
                return true;
                //q = pi[q];
            }
        }

        return false;
    }

    // pre[q] = k means P[0...k-1] is the maximum-length proper suffix of P[0...q-1], (k < q)
    // pre[0] is not used
    // pre[1] = 0;
    // for i = 1,...,N-1
    // suppose pre[i] = k, i.e. P[0...k-1] is the maximum-length proper suffix of P[0...i-1], (k is the length)
    // if P[i] == P[k], pre[i+1] = k + 1; 
    // if P[i] != P[k], k = pre[k], and check again
    //
    // Proof: O(N)
    // the only way that k increases is by k++, which executes at most once per iteration => the total increase in k is at most N-1
    // k is deducted in the while loop and k is never negative
    // => total decrease in k in the while loop is bounded by the total increase in k over all iterations of the for loop, which is N-1.
    private static int[] preprocess(String P) {
        int N = P.length();

        int[] pre = new int[N + 1];
        pre[1] = 0;

        int k = 0;
        for (int i = 1; i < N; i++) {
            while (k != 0 && P.charAt(i) != P.charAt(k)) {
                k = pre[k];
            }

            if (P.charAt(i) == P.charAt(k)) {
                k++;
            }

            pre[i + 1] = k;
        }

        return pre;
    }
}
