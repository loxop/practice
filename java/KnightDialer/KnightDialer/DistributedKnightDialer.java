package KnightDialer;

public class DistributedKnightDialer {
	public static final int MAX_THREAD_SIZE = 3;

	Map<Integer, List<Integer>> dict;
	long[][] move_map;

	// TODO: idle slave thread
	// TODO: workging slave thread

	public synchronized void setMoveMapItem(start, length, value) {
		move_map[start][length] = value;
	}

	public synchronized long getMoveMapItem(start, length) {
		return move_map[start][length];
	}

	public DistributedKnightDialer(Map<Integer, List<Integer>> dict, long[][] move_map){
		this.dict = dict;
		this.move_map = move_map;
		// TODO: init idle,working slaves
	}

	public long distributedKnightDialer(int start, int length) {
		// TODO: check if idle slave exists
		// 
	}
}

class DialWork {
	int start;
	int length;
	long result;

	public DialWork(int start, int length) {
		this.start = start;
		this.length = length;
	}

	public void setResult(long result) {
	}
}

class SlaveDialer extends Threads {
	DistributedKnightDialer parent;
	public SlaveDialer(DistributedKnightDialer parent) {
		this.parent = parent;
	}

	public void run() {
	}
}
