import java.util.Stack;

public class DFSTest {
	int[][] arr;
	boolean[] visited;

	void dfs_recur(int n) {
		if (visited[n])
			return;
		visited[n] = true;
		System.out.print(" "+n);
		for(int v: arr[n]){
			dfs_recur(v);
		}
	}

	void dfs_iter(int arr[][], int n) {
		boolean v[] = new boolean[arr.length];
		Stack<Integer> s = new Stack<Integer>();
		s.push(n);
		while(!s.empty()){
			int a = s.pop();
			if(v[a])
				continue;
			v[a] = true;
			System.out.print(" "+a);
			// easy way, but reverse order
			/*
			for(int i: arr[a]){
				s.push(i);
			}
			*/
			for(int i=arr[a].length - 1; i >= 0; i--){
				s.push(arr[a][i]);
			}
		}
	}

	public static void main(String[] args){
		DFSTest t = new DFSTest();
		t.arr = new int[][] { {}, {2,4}, {3,8}, {5}, {7}, {8}, {7,9}, {11,12}, {10}, {}, {9,13}, {13}, {}, {} };
		t.visited = new boolean[t.arr.length];

		t.dfs_recur(1);
		System.out.println("");
		t.dfs_iter(t.arr, 1);
		System.out.println("");

	}
}
