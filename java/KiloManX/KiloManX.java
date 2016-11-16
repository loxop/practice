import java.util.PriorityQueue;

public class KiloManX {
	public static void main(String[] args){
		KiloManX x = new KiloManX();
		String[] dc = {"070", "500", "140"};
		int[] bh = {150, 150, 150};
		System.out.println(x.leastShots(dc, bh));
	}

    public int leastShots(String[] damageChart, int[] bossHealth){
        // Prep: init values
        int n = damageChart.length;
        int weapons[] = new int[n];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        // Start: add start node into queue
        pq.add(new Node(0, 0, -1));
        
        // Loop: repeat until reach to the destination
        while(!pq.isEmpty()){
        	// pop head : visiting the boss
            Node head = pq.poll();
            
System.out.println("c: " + head.cost + ", w: " + head.weapon + ", b: " + head.boss);
        	// if this is the destination (all weapons gathered), return cost
            if (head.weapon == ((0x0001 << n) - 1)){
                return head.cost;
            }

			// Update weapon collection with strongest one from each weapon
            if (head.boss >= 0){
				System.out.print("w: ");
                for (int i = 0; i < n; i++) {
                    weapons[i] = Math.max(damageChart[head.boss].charAt(i) - '0', weapons[i]);
					System.out.print("" + weapons[i]);
                }
				System.out.println("");
            }
            
	        // list up all neighbors by adding cost to get there, add them into pq
            for (int i = 0; i < n; i++){
                if ( (head.weapon & (0x0001 << i)) == 0x0001) { // already visited node
                    continue;
                }
                // calculate min cost get to this neighbor
                int health = bossHealth[i];
				int minCost = health;
                if (weapons[i] > 0){
                    minCost = health / weapons[i];
                    minCost += (health % weapons[i] > 0)? 1 : 0;
                }
                pq.add(new Node(head.cost + minCost, head.weapon | (0x0001 << i), i));
            }
        }
	    return -1;
    }
}

class Node implements Comparable<Node>{
    public int cost;	// Total cost to get to this node since starting point
    public int weapon;	// Weapons gathered before this node, represented with bits (0~2^(n-1))
    public int boss;	// Last boss defeated
    
    Node(int c, int w, int b){
        cost = c;
        weapon = w;
        boss = b;
    }
    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}
