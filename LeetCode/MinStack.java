// https://leetcode.com/problems/min-stack/

import java.util.ArrayList;
import java.util.List;

public class MinStack {
    private int topIdx = -1;
    private List<Integer> items = new ArrayList<Integer>();

    private int minIdx = -1;
    private List<Integer> nextMin = new ArrayList<Integer>();

    public void push(int x) {
        topIdx++;
        if (items.size() < topIdx + 1) {
            items.add(0);
            nextMin.add(-1);
        }

        items.set(topIdx, x);

        if (minIdx == -1) {
            nextMin.set(topIdx, -1);
            minIdx = topIdx;
        } else {
            if (x < items.get(minIdx)) {
                nextMin.set(topIdx, minIdx);
                minIdx = topIdx;
            } else {
                nextMin.set(topIdx, -1);
            }
        }
    }

    public void pop() {
        items.set(topIdx, null);

        if (nextMin.get(topIdx) != -1) {
            minIdx = nextMin.get(topIdx);
        }
        nextMin.set(topIdx, null);

        topIdx--;
    }

    public int top() {
        return items.get(topIdx);
    }

    public int getMin() {
        return items.get(minIdx);
    }
}
