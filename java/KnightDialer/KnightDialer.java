import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class KnightDialer {
	public static final int PAD_WIDTH = 12;
	public static final int PAD_HEIGHT = 16;
	public static final int[][] POSSIBLE_MOVES = { {-1,-2}, {+1,-2}, {-2,-1}, {+2,-1}, {-2,+1}, {+2,+1}, {-1,+2}, {+1,+2} };

	public static int[][] dialpad = new int[PAD_WIDTH][PAD_HEIGHT];
	static {
		for (int j = 0; j < PAD_HEIGHT; j++){
			for (int i = 0; i < PAD_WIDTH; i++){
				dialpad[i][j] = j * PAD_WIDTH + i + 1;
			}
		}

	}

	public static Map<Integer, List<Integer>> dict = new HashMap<Integer, List<Integer>>();
	static {
		for (int j = 0; j < PAD_HEIGHT; j++) {
			for (int i = 0; i < PAD_WIDTH; i++) {
				int n = dialpad[i][j];
				List<Integer> next_nums = new ArrayList<Integer>();
				for (int[] move: POSSIBLE_MOVES){
					int x = i + move[0];
					int y = j + move[1];
					if (x >= 0 && x < PAD_WIDTH && y >= 0 && y < PAD_HEIGHT){
						next_nums.add(dialpad[x][y]);
					}
				}
				dict.put(n, next_nums);
			}
		}
	}

	public static void print_dialpad() {
		for (int j = 0; j < PAD_HEIGHT; j++) {
			for (int i = 0; i < PAD_WIDTH; i++) {
				System.out.format("%3d ", dialpad[i][j]);
			}
			System.out.println();
		}
	}

	public static void print_dict() {
		Integer[] key_array = dict.keySet().toArray(new Integer[0]);
		Arrays.sort(key_array);
		for (int key: key_array) {
			System.out.format("%d: %s\n", key, dict.get(key));
		}
	}

	public static void main(String[] args){
		// print_dialpad();
		// print_dict();
	}
}
