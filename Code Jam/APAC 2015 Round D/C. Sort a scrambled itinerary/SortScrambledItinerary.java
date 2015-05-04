import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SortScrambledItinerary {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("C-small.in.txt"));
        Scanner sc = new Scanner(new File("C-large.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        sc.nextLine();
        for (int t=1; t<=T; t++) {
            int N = sc.nextInt();
            sc.nextLine();
            
            Set<String> placesOnce = new HashSet<String>();
            Map<String, String> transfer = new HashMap<String, String>();
            for (int n=0; n<N; n++) {
                String source = sc.nextLine();
                String dest = sc.nextLine();
                
                if (placesOnce.contains(source)) {
                    placesOnce.remove(source);
                } else {
                    placesOnce.add(source);
                }
                
                if (placesOnce.contains(dest)) {
                    placesOnce.remove(dest);
                } else {
                    placesOnce.add(dest);
                }
                
                transfer.put(source, dest);
            }
            
            out.printf("Case #%d: %s\n", t, process(placesOnce, transfer));
        }
        
        out.flush();
        out.close();
    }
    
    private static String process(Set<String> placesOnce, Map<String, String> transfer) {
        String start = null;
        
        List<String> placesOnceList = new LinkedList<String>(placesOnce);
        if (transfer.containsKey(placesOnceList.get(0))) {
            start = placesOnceList.get(0);
        } else if (transfer.containsKey(placesOnceList.get(1))) {
            start = placesOnceList.get(1);
        }
        
        StringBuilder sb = new StringBuilder();
        String source = start;
        while (transfer.containsKey(source)) {
            String dest = transfer.get(source);
            sb.append(source).append('-').append(dest).append(' ');
            source = dest;
        }
        
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }
}
