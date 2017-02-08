import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] visited;
	static Map<Integer, List<Integer>> map;
	static List<Integer> graphs;

	static long combinations(int N) {
		// handle corner case : N = 0, 1 / I = 0, 1
		for(int i = 0; i < N; i++) {
			if (visited[i]){
				continue;
			}

			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(i);
			int graph_size = 0;
			while(!q.isEmpty()){
				int node = q.poll();
				if (visited[node]){
					continue;
				}
				visited[node] = true;
				graph_size++;
				if (map.get(node) != null) {
					for (int child: map.get(node)){
						q.offer(child);
					}
				}
			}
			graphs.add(graph_size);
		}

		
		long total = 0;
		int remain = N;

		for (int i = 0; i < graphs.size(); i++){
			int graph_size = graphs.get(i);
			total += (remain - graph_size) * graph_size;
			remain -= graph_size;
		}

		for (int i = remain - 1; i > 0; i--){
			total += i;
		}

		return total;
	}

   public static void main(String[] args) throws Exception{
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int I = Integer.parseInt(temp[1]);
      
		map = new HashMap<Integer, List<Integer>>();
		visited = new boolean[N];
		graphs = new ArrayList<Integer>();

        for(int i = 0; i < I; i++){
            temp = bfr.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
          // Store a and b in an appropriate data structure of your choice
			List<Integer> list_a = map.get(a);
			if (list_a == null){
				list_a = new LinkedList<Integer>();
				map.put(a, list_a);
			}
			list_a.add(b);

			List<Integer> list_b = map.get(b);
			if (list_b == null){
				list_b = new LinkedList<Integer>();
				map.put(b, list_b);
			}
			list_b.add(a);
        }
   
        long combinations = 0;

        // Compute the final answer - the number of combinations
		combinations = combinations(N);
       
        System.out.println(combinations);

    }
}

