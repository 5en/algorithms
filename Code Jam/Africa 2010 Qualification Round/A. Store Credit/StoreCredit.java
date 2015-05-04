// could be improved using hashing

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class StoreCredit {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int N = sc.nextInt();
		for (int n=1; n<=N; n++) {
			int C = sc.nextInt();
			int I = sc.nextInt();
			Item[] price = new Item[I];
			for (int i=0; i<I; i++) {
				price[i] = new Item(sc.nextInt(), i+1);
			}
			
			out.printf("Case #%d: %s\n", n, process(C, I, price));
		}

		out.flush();
		out.close();
	}
	
	private static String process(int C, int I, Item[] price) {
	    Arrays.sort(price);
        
        int result = Arrays.binarySearch(price, new Item(C, -1));
        int begin = 0;
        int end = 0;
        if (result >= 0) {
            end = result - 1;
        }
        else {
            end = (-result-1) - 1;
        }
        
        while (price[begin].value + price[end].value != C) {
            if (price[begin].value + price[end].value > C) {
                end--;
            }
            else {
                begin++;
            }
        }
        
        int index1 = price[begin].index;
        int index2 = price[end].index;
        
        return String.format("%d %d", (index1>index2 ? index2: index1), (index1>index2 ? index1: index2));
	}
	
	private static class Item implements Comparable<Item> {
		public final int value;
		public final int index;
		
		public Item(int value, int index) {
			this.value = value;
			this.index = index;
		}
		
		@Override
		public int compareTo(Item o) {
			return this.value - o.value;
		}
	}
}
