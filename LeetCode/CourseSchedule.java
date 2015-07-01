import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Vertex> label2Vertex = new HashMap<Integer, Vertex>();
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[0];
            if (!label2Vertex.containsKey(from)) {
                label2Vertex.put(from, new Vertex());
            }
            int to = prerequisite[1];
            if (!label2Vertex.containsKey(to)) {
                label2Vertex.put(to, new Vertex());
            }
            label2Vertex.get(from).adjs.add(label2Vertex.get(to));
        }

        if (new Random().nextInt() % 2 == 0) {
            for (Vertex v : label2Vertex.values()) {
                if (dfs(v) == false) {
                    return false;
                }
            }
            return true;
        } else {
            Set<Vertex> visiting = new HashSet<Vertex>();
            Set<Vertex> visited = new HashSet<Vertex>();
            for (Vertex v : label2Vertex.values()) {
                if (dfs(v, visiting, visited) == false) {
                    return false;
                }
            }
            return true;
        }
    }

    // utilize Vertex.status
    private static boolean dfs(Vertex v) {
        if (v.status == Status.VISITING) {
            return false;
        }
        if (v.status == Status.VISITED) {
            return true;
        }

        v.status = Status.VISITING;
        for (Vertex adj : v.adjs) {
            if (dfs(adj) == false) {
                return false;
            }
        }
        v.status = Status.VISITED;

        return true;
    }

    // use Set instead of Vertex.status
    private static boolean dfs(Vertex v, Set<Vertex> visiting, Set<Vertex> visited) {
        if (visiting.contains(v)) {
            return false;
        }
        if (visited.contains(v)) {
            return true;
        }

        visiting.add(v);
        for (Vertex adj : v.adjs) {
            if (dfs(adj, visiting, visited) == false) {
                return false;
            }
        }
        visiting.remove(v);
        visited.add(v);

        return true;
    }

    private static class Vertex {
        List<Vertex> adjs = new LinkedList<Vertex>();
        Status status = Status.NOT_VISITED;
    }

    private static enum Status {
        NOT_VISITED, VISITING, VISITED;
    }
}
