import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class KnightDialer {
	/* Init constants */
	public static final int PAD_WIDTH = 12;
	public static final int PAD_HEIGHT = 16;
	public static final int[][] POSSIBLE_MOVES = { {-1,-2}, {+1,-2}, {-2,-1}, {+2,-1}, {-2,+1}, {+2,+1}, {-1,+2}, {+1,+2} };

	/* build up 12x16 dialpad */
	public static int[][] dialpad = new int[PAD_WIDTH][PAD_HEIGHT];
	static {
		for (int j = 0; j < PAD_HEIGHT; j++){
			for (int i = 0; i < PAD_WIDTH; i++){
				dialpad[i][j] = j * PAD_WIDTH + i + 1;
			}
		}
	}

	/* build up next move dict */
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

	public static long[][] move_map = null;

	public static void init_move_map(int max_length) {
		move_map = new long[PAD_WIDTH * PAD_HEIGHT + 1][max_length];
		for (int i = 0; i < move_map.length; i++){
			for (int j = 0; j < move_map[i].length; j++){
				move_map[i][j] = -1;
			}
		}
	}

	public static long simple_knight_dialer(int start, int length) {
		if (move_map == null){
			init_move_map(length + 1);
		}
		if (length == 1) {
			return dict.get(start).size();
		}
		if (move_map[start][length] < 0){
			long sum = 0;
			for (int next: dict.get(start)) {
				sum += simple_knight_dialer(next, length - 1);
			}
			move_map[start][length] = sum;
		}
		return move_map[start][length];
	}

	public static long multi_knight_dialer(int start, int length) {

	}

	public static void main(String[] args){
		int start = 1;
		int length = 10;

		if (args.length == 1) {
			if (args[0].equals("pad")) {
				print_dialpad();
				return;
			} else if (args[0].equals("dict")) {
				print_dict();
				return;
			}
		}

		if (args.length == 2) {
			start = Integer.parseInt(args[0]);
			length = Integer.parseInt(args[1]);
		}

		System.out.format("%d\n", simple_knight_dialer(start, length));
	}

	/* util functions */
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
}
