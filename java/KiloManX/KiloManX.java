import java.util.PriorityQueue;

public class KiloManX {
	public static void main(String[] args){
		KiloManX x = new KiloManX();
		// String[] dc = {"070", "500", "140"};
		// int[] bh = {150, 150, 150};

		String[] dc = {"198573618294842", "159819849819205", "698849290010992", "000000000000000", "139581938009384", "158919111891911", "182731827381787", "135788359198718", "187587819218927", "185783759199192", "857819038188122", "897387187472737", "159938981818247", "128974182773177", "135885818282838"};
		int[] bh = {157, 1984, 577, 3001, 2003, 2984, 5988, 190003,9000, 102930, 5938, 1000000, 1000000, 5892, 38};
		System.out.println(x.leastShots(dc, bh));
	}

    public int leastShots(String[] damageChart, int[] bossHealth){
        // Prep: init values
        int n = damageChart.length;
		int[] visited = new int[0x0001 << n];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        // Start: add start node into queue
        pq.add(new Node(0, 0));
        
        // Loop: repeat until reach to the destination
        while(!pq.isEmpty()){
        	// pop head : visiting the boss
            Node head = pq.poll();
            
        	// if this is the destination (all weapons gathered), return cost
            if (head.weapon == ((0x0001 << n) - 1)){
                return head.cost;
            }
			if (visited[head.weapon] == 1){
				continue;
			}
			visited[head.weapon] = 1;

            
	        // list up all neighbors by adding cost to get there, add them into pq
            for (int i = 0; i < n; i++){
                if ( ((head.weapon >> i) & 0x0001) == 0x0001) { // already visited node
                    continue;
                }
                // calculate min cost get to this neighbor
                int health = bossHealth[i];
				int minCost = health;
				for(int j = 0; j < n; j++){
					int force = damageChart[j].charAt(i) - '0';
					if (i != j && (((head.weapon >> j) & 0x0001) == 0x0001) && force > 0){
						int cost = health / force;
						cost += (health % force > 0)? 1 : 0;
						minCost = (cost < minCost)? cost : minCost;
					}
				}
                pq.add(new Node(head.cost + minCost, head.weapon | (0x0001 << i)));
            }
        }
	    return -1;
    }
}

class Node implements Comparable<Node>{
    public int cost;	// Total cost to get to this node since starting point
    public int weapon;	// Weapons gathered before this node, represented with bits (0~2^(n-1))
    
    Node(int c, int w){
        cost = c;
        weapon = w;
    }
    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}
