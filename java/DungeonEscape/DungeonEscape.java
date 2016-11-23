import java.util.PriorityQueue;

public class DungeonEscape {
	public static void main(String[] args){
	}

    public int exitTime(String[] up, String[] down, String[] east, String[] west, int startLevel, int startEasting){
        int depth = up.length;
        int breadth = up[0].length();
        int[][] visitMap = new int[depth][breadth];
        PriorityQueue<Room> pq = new PriorityQueue<Room>();
        
        // make start node and put it to PQ
        
        // while loop
        // node = pq pop
        //	ending condition: room.level == -1 or...
        //  check: room.level
        
        return -1;
    }
}

class Room implements Comparable<Room>{
    int level;
    int easting;
    int cost;	// Cost to get here
    Room[] rooms;
    
    Room (int l, int e, int c, Rooms r){
        level = l;
        easting = e;
        cost = c;
        rooms = r;
    }
    
    public int compareTo(Room r){
        return cost - r.cost;
    }
}
