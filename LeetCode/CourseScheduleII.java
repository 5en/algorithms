// https://leetcode.com/problems/course-schedule-ii/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Vertex> label2Vertex = new HashMap<Integer, Vertex>();
        for (int i = 0; i < numCourses; i++) {
            label2Vertex.put(i, new Vertex(i));
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1]; // prerequisite course
            int to = prerequisite[0];
            label2Vertex.get(from).adjs.add(label2Vertex.get(to));
        }
        
        List<Vertex> ordered = new LinkedList<Vertex>();
        for (Vertex v : label2Vertex.values()) {
            if (dfs(v, ordered) == false) {
                return new int[0];
            }
        }
        
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = ordered.get(i).data;
        }
        
        return result;
    }
    
    private static boolean dfs(Vertex v, List<Vertex> ordered) {
        if (v.status == Status.VISITING) {
            return false;
        }
        if (v.status == Status.VISITED) {
            return true;
        }
        
        v.status = Status.VISITING;
        for (Vertex adj : v.adjs) {
            if (dfs(adj, ordered) == false) {
                return false;
            }
        }
        v.status = Status.VISITED;
        
        ordered.add(0, v); // Add the newly finished vertex to the front
        
        return true;
    }
    
    private static class Vertex {
        int data;
        List<Vertex> adjs = new LinkedList<Vertex>();
        Status status = Status.NOT_VISITED;
        
        public Vertex(int data) {
            this.data = data;
        }
    }

    private static enum Status {
        NOT_VISITED, VISITING, VISITED;
    }
}
