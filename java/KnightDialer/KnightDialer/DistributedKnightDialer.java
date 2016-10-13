package KnightDialer;

public class DistributedKnightDialer {
	Map<Integer, List<Integer>> dict;
	long[][] move_map;

	public DistributedKnightDialer(Map<Integer, List<Integer>> dict, long[][] move_map){
		this.dict = dict;
		this.move_map = move_map;
	}

	public long distributedKnightDialer(int start, int length) {

	}
}

class SlaveDialer extends Threads {
	public void run() {
	}
}
