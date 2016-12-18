package DungeonEscape;

import java.util.PriorityQueue;

public class DungeonEscape {
	/*
	public static void main(String[] args) {
		DungeonEscape escape = new DungeonEscape();
		String[] up = { "0x4", "0x3", "0x3"};
		String[] down = { "0x9", "009", "0x9"};
		String[] east = { "0x9", "1x9", "009"};
		String[] west = { "0x9", "0x0", "009"};
		int startLevel = 2;
		int startEasting = 2;
		int result = escape.exitTime(up, down, east, west, startLevel, startEasting);
		System.out.println("Result : " + result);
	}
	*/

	int[][] dir = { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };

	public int exitTime(String[] up, String[] down, String[] east, String[] west, int startLevel, int startEasting){
		// init variables
		int depth = up.length;
		int width = up[0].length();
		boolean[][] visitMap = new boolean[depth][width];
		PriorityQueue<Room> pq = new PriorityQueue<Room>();
		
		// make start node and put it to PQ
		pq.add(new Room(startLevel, startEasting, 0));
		
		int level, easting, cost;
		char[] rooms = new char[4];

		char chRoom;
		int nextLevel, nextEasting, nextCost;
		// while pq is not empty
		while(!pq.isEmpty()){
			// node = pq poll
			Room room = pq.poll();
			level = room.level;
			easting = room.easting;
			cost = room.cost;
			if (level == -1){
				//	ending condition: room.level == -1
				return cost;
			}

			if (visitMap[level][easting]){
				continue;
			}
			visitMap[level][easting] = true;

			// add all available rooms // check boundary, validity
			rooms[0] = up[level].charAt(easting);
			rooms[1] = down[level].charAt(easting);
			rooms[2] = east[level].charAt(easting);
			rooms[3] = west[level].charAt(easting);
			
			for (int i = 0; i < 4; i++){
				if (rooms[i] < '0' || rooms[i] > '9'){
					continue; // no path
				}

				nextLevel = level + dir[i][0];
				nextEasting = easting + dir[i][1];
				nextCost = cost + rooms[i] - '0';

				if (nextLevel >= depth || nextEasting >= width || nextEasting < 0){
					continue; // crosses boundary
				}
				if ( nextLevel >= 0 && ( (depth - nextLevel) * width <= nextCost)) {
					continue; // drown
				}

				pq.add(new Room(nextLevel, nextEasting, nextCost));
			}
		}

		// No possible escape path exists
		return -1;
	}
}

class Room implements Comparable<Room> {
	int level;
	int easting;
	int cost;	// Cost to get here
	
	Room (int l, int e, int c){
		level = l;
		easting = e;
		cost = c;
	}
	
	public int compareTo(Room r){
		return cost - r.cost;
	}
}
