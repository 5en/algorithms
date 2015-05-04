import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BadHorse {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("A-small-practice-2.in.txt"));
		Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int M = sc.nextInt();
			sc.nextLine();
			
			Map<Node, List<Node>> graph = new HashMap<Node, List<Node>>();
			for (int i=0; i<M; i++) {
				String[] names = sc.nextLine().split(" ");
				Node node0 = NodeFactory.getNode(names[0]);
				Node node1 = NodeFactory.getNode(names[1]);
				if (!graph.containsKey(node0)) {
					graph.put(node0, new LinkedList<Node>());
				}
				if (!graph.containsKey(node1)) {
					graph.put(node1, new LinkedList<Node>());
				}
				graph.get(node0).add(node1);
				graph.get(node1).add(node0);
			}
			
			out.printf("Case #%d: %s\n", t, isBipartite(graph));
			
			NodeFactory.clear();
		}
		
		out.flush();
		out.close();
	}
	
	private static String isBipartite(Map<Node, List<Node>> graph) {
		for (Node node : graph.keySet()) {
			if (node.color == 0) {
				node.color = 1;
				if (bfs(graph, node) == false) {
					return "No";
				}
			}
		}
		
		return "Yes";
	}
	
	private static boolean bfs(Map<Node, List<Node>> graph, Node root) {
		Queue<Node> queue = new LinkedList<BadHorse.Node>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node parent = queue.remove();
			
			for (Node child : graph.get(parent)) {
				if (child.color == parent.color) {
					return false;
				}
				
				if (child.color == -parent.color) {
					continue;
				}
				
				// child.color == 0
				child.color = -parent.color;
				
				queue.add(child);
			}
		}
		
		return true;
	}
	
	private static class NodeFactory {
		private static HashMap<String, Node> nodes = new HashMap<String, BadHorse.Node>();
		
		public static Node getNode(String name) {
			if (!nodes.containsKey(name)) {
				nodes.put(name, new Node(name));
			}
			
			return nodes.get(name);
		}
		
		public static void clear() {
			nodes.clear();
		}
	}
	
	private static class Node {
		public int color = 0;
		public String name;
		
		public Node(String name) {
			this.name = name;
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) { return true; }
			
			if (o == null) { return false; }
			
			if (this.getClass() != o.getClass()) { return false; }
			
			return this.name==((Node) o).name;
		}
	}
}
