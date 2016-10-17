package KnightDialer;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class DistributedKnightDialer {
	public static final int MAX_THREAD_SIZE = 4;

	Map<Integer, List<Integer>> dict;
	long[][] move_map;

	int working_slaves;

	public synchronized void setMoveMapItem(int start, int length, long value) {
		move_map[start][length] = value;
	}

	public synchronized long getMoveMapItem(int start, int length) {
		return move_map[start][length];
	}

	public List<Integer> getDictItem(int size) {
		return dict.get(size);
	}

	/*
	* Instead of implementing thread pool, just controlled max number of running threads
	*/
	public synchronized SlaveThread allocateSlave(int start, int length) {
		if (working_slaves >= MAX_THREAD_SIZE) {
			return null;
		}
		working_slaves++;
		// System.out.format("New: %d, %d / total: %d\n", start, length, working_slaves);
		return new SlaveThread(this, start, length);
	}

	public synchronized void releaseSlave() {
		working_slaves--;
		// System.out.format("released / total: %d\n", working_slaves);
	}

	public DistributedKnightDialer(Map<Integer, List<Integer>> dict, long[][] move_map){
		this.dict = dict;
		this.move_map = move_map;
		working_slaves = 0;
	}

	public long distributedKnightDialer(int start, int length) {
		SlaveThread slave = allocateSlave(start, length);
		slave.start();
		try {
			slave.join();
		} catch (Exception e) {
			System.out.println("Error: exception");
		}
		return slave.getResult();
	}
}

class SlaveThread extends Thread {
	DistributedKnightDialer parent;
	int start;
	int length;
	long result;

	public SlaveThread(DistributedKnightDialer parent, int start, int length) {
		this.parent = parent;
		this.start = start;
		this.length = length;
		result = -1;
	}

	public void run() {
		result = knightDialer(start, length);
		parent.releaseSlave();
	}

	public long getResult(){
		return result;
	}

	public long knightDialer(int start, int length) {
		if (length == 1) {
			return parent.getDictItem(start).size();
		}
		/*
		*  In real distributed environment, getMoveMapItem() may cost a lot because of network transfer.
		*  In that case, we might set a threshold for 'length', so that 
		*		a) length < threshold : calculate the result, no matter if it exists in master's map
		*		b) length > threshold : request the result to master, and go on calculating if not exist
		*/
		if (parent.getMoveMapItem(start, length) < 0) {
			long sum = 0;
			List<SlaveThread> mySlaves = new LinkedList<SlaveThread>();
			for (int next: parent.getDictItem(start)) {
				SlaveThread slave = parent.allocateSlave(next, length - 1);
				if (slave != null) {
					mySlaves.add(slave);
					slave.start();
				} else {
					sum += knightDialer(next, length - 1);
				}
			}
			for (SlaveThread slave: mySlaves) {
				try {
					slave.join();
				} catch(Exception e) {
					System.out.println("Error: exception");
				}
				sum += slave.getResult();
			}
			parent.setMoveMapItem(start, length, sum);
		}
		return parent.getMoveMapItem(start, length);
	}
}

