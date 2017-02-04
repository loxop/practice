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
		int[] a = {100,200,300,1200,6000};
		int[] b = {};
		int[] c = {900,902,1200,4000,5000,6001};
		int[] d = {0,2000,6002};
		int[] e = {1,2,3,4,5,6,7,8};

		int credit = x.maxCredit(a, b, c, d, e);
		System.out.println(credit);
	}

	int nextThird(Press[] p, int t) {
		HashSet<Integer> j = new HashSet<Integer>();
		j.add(p[t].judge);
		for (int i = t; i < p.length; i++){
			j.add(p[i].judge);
			if (j.size() >= 3){
				return i;
			}
		}
		return -1;
	}

	int nextTime(Press[] p, int t) {
		int i = t;
		while(i < p.length && p[i].time == p[t].time){
			i++;
		}
		return i;
	}

	public int maxCredit(int[] a, int[] b, int[] c, int[] d, int[] e){
		ArrayList<Press> list = new ArrayList<Press>();
		int[][] arrays = new int[][]{ a, b, c, d, e };
		for (int i = 0; i < arrays.length; i++){
			for(int j = 0; j < arrays[i].length; j++){
				list.add(new Press(arrays[i][j], i));
			}
		}
		Press[] p = list.toArray(new Press[]{});
		Arrays.sort(p);

		int credit = 0;
		for(int i = 0; i < p.length; i++) {
			int n = nextThird(p, i);
			if (n < 0){
				continue;
			}
			if (p[n].time - p[i].time > 1000){
				continue;
			}
			credit++;
			i = nextTime(p, n) - 1;
		}
		return credit;
	}
}
