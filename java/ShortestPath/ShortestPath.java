// import java.util.Comparable;
import java.util.PriorityQueue;

public class ShortestPath {
	static Node dijkstra(int s, int q, int[][] G){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s, 0, null));
		while (pq.size() > 0) {
			Node n = pq.poll();
			if (n.n == q) {
				return n;
			}
			for (int i = 0; i < G[n.n].length; i++){
				if (G[n.n][i] > 0) {
					pq.add(new Node(i, G[n.n][i] + n.d, n));
				} // if 
			} // for
		} // while
		return null;
	} // function

	public static void main(String args[]){
		int[][] G = { {0, 10, 30, 15, -1, -1},
					  {-1, 0, -1, -1, 20, -1},
					  {-1, -1, 0, -1, -1, 5},
					  {-1, -1, 5, 0, -1, 20},
					  {-1, -1, -1, -1, 0, 20},
					  {-1, -1, -1, 20, -1, 0} };
		Node n = dijkstra(0, 5, G);
		System.out.println("Shortest distance : " + n.d);
		String path = "" + n.n;
		while(n.p != null){
			n = n.p;
			path = n.n + " => " + path;
		}
		System.out.println(path);
	}
}

class Node implements Comparable<Node> {
	int n;
	int d;
	Node p;

	Node(int n, int d, Node p){
		this.n = n;
		this.d = d;
		this.p = p;
	}

	public int compareTo(Node o){
		return d - o.d;
	}
}

