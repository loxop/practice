import java.util.Stack;

public class Marketing {
	int[][] am; // Adjacency Matrix
	int[] vm;	// Visit Map

	// Do DFS with marking 1 or 2 as Adult or Teenager
	boolean checkGroup(int n){
		Stack<Integer> s = new Stack<Integer>();
		vm[n] = 1;
		s.push(n);
		while(!s.empty()){
			int k = s.pop();
			int f = vm[k] == 2? 1: 2;
			for (int i = 0; i < am[k].length; i++){
				if(am[k][i] == 1){
					if (vm[i] == 0) {
						// unvisited neighbor
						vm[i] = f;
						s.push(i);
					} else {
						// visited
						if (f != vm[i]){
							// conflict
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public long howMany(String[] compete){
		int n = compete.length;
		am = new int[n][n];
		vm = new int[n];

		// Construct the Graph's adjacency matrix
		for (int i = 0; i < n; i++){
			String[] sp = compete[i].split(" ");
			for(String s : sp){
				if (s.length() > 0){
					int k = Integer.parseInt(s);
					am[k][i] = 1;
					am[i][k] = 1;
				}
			}
		}

		int ng = 0; // Number of Groups
		for (int i = 0; i < n; i++){
			if (vm[i] == 0){
				if (checkGroup(i)) {
					ng++;
				} else {
					return -1;
				}
			}
		}

		return (long)Math.pow(2, ng);
	}

	public static void main(String[] args){
		//String[] c = new String[]{"1 4", "2", "3", "0", ""};
		//String[] c = new String[]{"1", "2", "0"};
		//String[] c = new String[]{"1", "2", "3", "0", "0 5", "1"};
		//String[] c = new String[]{"","","","","","","","","","",
		//							"","","","","","","","","","",
		//							"","","","","","","","","",""};
		String[] c = new String[]{"1", "2", "3", "0", "5", "6", "4"};

		Marketing m = new Marketing();
		System.out.println(m.howMany(c));
	}
}
