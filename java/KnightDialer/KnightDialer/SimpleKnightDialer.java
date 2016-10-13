package KnightDialer;

import java.util.Map;
import java.util.List;

public class SimpleKnightDialer {
	Map<Integer, List<Integer>> dict;
	long[][] move_map;

	public SimpleKnightDialer(Map<Integer, List<Integer>> dict, long[][] move_map){
		this.dict = dict;
		this.move_map = move_map;
	}

	public long simpleKnightDialer(int start, int length) {
		if (move_map == null || dict == null) {
			return -1;
		}
		if (length == 1) {
			return dict.get(start).size();
		}
		if (move_map[start][length] < 0){
			long sum = 0;
			for (int next: dict.get(start)) {
				sum += simpleKnightDialer(next, length - 1);
			}
			move_map[start][length] = sum;
		}
		return move_map[start][length];
	}
}
