import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RopeIntranet {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int[] A = new int[N];
			int[] B = new int[N];
			for (int n=0; n<N; n++) {
				A[n] = sc.nextInt();
				B[n] = sc.nextInt();
			}
			
			out.printf("Case #%d: %d\n", t, process(N, A, B));
		}
		
		out.flush();
		out.close();
	}

	private static int process(int N, int[] A, int[] B) {
	    int[] idx = sort(A);
        int[] newB = new int[N];
        for (int n=0; n<N; n++) {
            newB[n] = B[idx[n]];
        }
        
        return reverseOrdered(newB, 0, N-1);
	}
	
	private static int[] sort(int[] a) {
		int[] idx = new int[a.length];
		for (int i=0; i<a.length; i++) {
			idx[i] = i;
		}
		
		quickSort(idx, a, 0, a.length-1);
		
		return idx;
	}
	
	// inclusive [left, right]
	private static void quickSort(int[] idx, int[] a, int left, int right) {
		if (left < right) {
			int pivotIdx = partition(idx, a, left, right);
			quickSort(idx, a, left, pivotIdx-1);
			quickSort(idx, a, pivotIdx+1, right);
		}
	}
	
	private static int partition(int[] idx, int[] a, int left, int right) {
		if (left == right) {
			return left;
		}
		
		int pivotIdx = choosePivot(a, left, right);
		// swap a[pivotIdx] and a[right]
		int pivotValue = a[pivotIdx];
		a[pivotIdx] = a[right];
		a[right] = pivotValue;
		// swap idx[pivotIdx] and idx[right]
		int idxPivot = idx[pivotIdx];
		idx[pivotIdx] = idx[right];
		idx[right] = idxPivot;
		
		// determine the index to store the pivot value
		pivotIdx = left;
		
		// invariant
		// a[left...pivotIdx-1] <= pivotValue
		// a[pivotIdx...right-1] > pivotValue

		for (int i=left; i<=right-1; i++) {
			if (a[i] <= pivotValue) {
				// swap a[i] and a[pivotIdx] 
				int tmp = a[pivotIdx];
				a[pivotIdx] = a[i];
				a[i] = tmp;
				// swap idx[i] and idx[pivotIdx] 
				tmp = idx[pivotIdx];
				idx[pivotIdx] = idx[i];
				idx[i] = tmp;

				pivotIdx++;
			}
		}
		
		// swap a[pivotIdx] with a[right]
		a[right] = a[pivotIdx];
		a[pivotIdx] = pivotValue;
		// swap idx[pivotIdx] with idx[right]
		idx[right] = idx[pivotIdx];
		idx[pivotIdx] = idxPivot;
		
		return pivotIdx;
	}
	
	private static int choosePivot(int[] a, int left, int right) {
		Random r = new Random();
		
		return left + r.nextInt(right-left+1);
	}
	
	private static int reverseOrdered(int[] a, int left, int right) {
		if (left == right) {
			return 0;
		}
		else if (left == right-1) {
			if (a[left] > a[right]) {
				// sort
				int tmp = a[left];
				a[left] = a[right];
				a[right] = tmp;
				
				return 1;
			}
		}
		
		int count = 0;
		int mid = (left + right) / 2;
		count += reverseOrdered(a, left, mid);
		count += reverseOrdered(a, mid+1, right);
		
		// revised version of merge sort
		int[] tmp = new int[right-left+1];
		int tmpi = 0;
		int li = left;
		int ri = mid+1;
		while (li<=mid && ri<=right) {
			if (a[li] <= a[ri]) {
				tmp[tmpi++] = a[li++];
			}
			else {
				// a[li] > a[ri]
				
				tmp[tmpi++] = a[ri];
				
				// # of reverse ordered paris = (a[li], a[ri]), (a[li+1], a[ri]), ..., (a[mid], a[ri])
				count += (mid-li+1);
				
				ri++;
			}
		}
		if (li > mid) {
			while (ri <= right) {
				tmp[tmpi++] = a[ri++];
			}
		}
		if (ri > right) {
			while (li <= mid) {
				tmp[tmpi++] = a[li++];
			}
		}
		
		for (tmpi=0; tmpi<tmp.length; tmpi++) {
			a[left+tmpi] = tmp[tmpi];
		}
		
		return count;
	}
}
