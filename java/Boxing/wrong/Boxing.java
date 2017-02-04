import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

class Press implements Comparable<Press> {
	int time;
	int judge;
	Press(int t, int j) {
		time = t;
		judge = j;
	}

	public int compareTo(Press p){
		return time - p.time;
	}
}

public class Boxing {
	public static void main (String[] args){
		Boxing x = new Boxing();
		int[] a = {5000, 6500};
		int[] b = {6000};
		int[] c = {6500};
		int[] d = {6000};
		int[] e = {0,5800,6000};

		int credit = x.maxCredit(a, b, c, d, e);
		System.out.println(credit);
	}

	int maxCredit(int[] a, int[] b, int[] c, int[] d, int[] e){
		// init : make sorted list of Presses
		ArrayList<Press> list = new ArrayList<Press>();

		int[][] arrays = new int[][]{ a, b, c, d, e };
		for (int i = 0; i < arrays.length; i++){
			for(int j = 0; j < arrays[i].length; j++){
				list.add(new Press(arrays[i][j], i));
			}
		}

		Press[] p = list.toArray(new Press[]{});
		Arrays.sort(p);

		HashSet<Integer> judges = new HashSet<Integer>();
		int start = 0;
		int current = 0;
		int credit = 0;

		judges.add(p[start].judge);
		while (start < p.length) {
			// take next item
			current++;

			// check end condition: exceed window size or meet the end of the array
			if (current >= p.length || p[current].time - p[start].time > 1000) {
				while(start < p.length - 1 && p[start].time == p[start+1].time) {
					start++;
				}
				start++;
				current = start;
				judges = new HashSet<Integer>();
				if (start < p.length){
					judges.add(p[start].judge);
				}
				continue;
			}

			// otherwise, check judge number
			judges.add(p[current].judge);

			while(current < p.length - 1 && p[current].time == p[current+1].time) {
				judges.add(p[current].judge);
				current++;
			}

			if (judges.size() >= 3) {
				credit++;
				start = current + 1;
				current = start;
				judges = new HashSet<Integer>();
				if (start < p.length) {
					judges.add(p[start].judge);
				}
			}
		}

		return credit;
	}
}
