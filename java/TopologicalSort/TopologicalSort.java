import java.util.List;
import java.util.LinkedList;

public class TopologicalSort {
	int[][] arr;
	boolean[] visited;

	int next_leaf(int n) {
		for (int v: arr[n]){
			if (!visited[v]){
				return next_leaf(v);
			}
		}
		visited[n] = true;
		return n;
	}

	void topological_sort() {
		List<Integer> remain = new LinkedList<Integer>();
		for (int i = 1; i < arr.length; i++){
			remain.add(i);
		}
		List<Integer> result = new LinkedList<Integer>();
		while (!remain.isEmpty()){
			int l = next_leaf(remain.get(0));
			result.add(0, l);
			remain.remove(new Integer(l));
		}
		for (int v: result) {
			System.out.print(v + " ");
		}
		System.out.println("");
	}

	public static void main (String[] args){
		TopologicalSort sort = new TopologicalSort();
		sort.arr = new int[][] { {}, {2}, {3}, {4}, {5}, {}, {7, 8, 9}, {1}, {2}, {4}, {3} };
		sort.visited = new boolean[sort.arr.length];
		sort.topological_sort();
	}
}
