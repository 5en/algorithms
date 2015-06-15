// https://leetcode.com/problems/rectangle-area/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectangleArea {
    public static class Rect {
        public final int xlow;
        public final int ylow;
        public final int xhigh;
        public final int yhigh;
        
        public Rect(int xlow, int ylow, int xhigh, int yhigh) {
            this.xlow = xlow;
            this.ylow = ylow;
            this.xhigh = xhigh;
            this.yhigh = yhigh;
        }
        
        public boolean contains(Rect rect) {
            return this.xlow <= rect.xlow && this.xhigh >= rect.xhigh && this.ylow <= rect.ylow && this.yhigh >= rect.yhigh;
        }
        
        public int area() {
            return (xhigh - xlow) * (yhigh - ylow);
        }
    }
    
    // O(N^3) time
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Rect rect1 = new Rect(A, B, C, D);
        Rect rect2 = new Rect(E, F, G, H);
        List<Rect> rects = new ArrayList<Rect>();
        rects.add(rect1);
        rects.add(rect2);
        
        List<Integer> xs = new ArrayList<Integer>();
        List<Integer> ys = new ArrayList<Integer>();
        for (Rect rect : rects) {
            xs.add(rect.xlow);
            xs.add(rect.xhigh);
            ys.add(rect.ylow);
            ys.add(rect.yhigh);
        }
        Collections.sort(xs);
        Collections.sort(ys);
        
        int sum = 0;
        for (int xi = 0; xi < xs.size()-1; xi++) {
            for (int yi = 0; yi < ys.size()-1; yi++) {
                int xlow = xs.get(xi);
                int xhigh = xs.get(xi+1);
                int ylow = ys.get(yi);
                int yhigh = ys.get(yi+1);
                Rect cell = new Rect(xlow, ylow, xhigh, yhigh);
                for (Rect rect : rects) {
                    if (rect.contains(cell)) {
                        sum += cell.area();
                        break;
                    }
                }
            }
        }
        
        return sum;
    }
}
