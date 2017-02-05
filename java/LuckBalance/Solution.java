import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Luck implements Comparable<Luck> {
	int l;
	int t;
	Luck(int l, int t){
		this.l = l;
		this.t = t;
	}
	public int compareTo(Luck l) {
		return l.l - this.l;
	}
}

public class Solution {
	static int luckBalance(Luck[] l, int k) {
		Arrays.sort(l);
		int m = 0; // max luck
		int c = 0; // important count
		for (int i = 0; i < l.length; i++) {
			if (l[i].t == 1) {
				c++;
				if (c > k) {
					m -= l[i].l;
					continue;
				}
			}
			m += l[i].l;
		}
		return m;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
		Luck[] array = new Luck[n];
		for (int i = 0; i < n; i ++) {
			int l = in.nextInt();
			int t = in.nextInt();
			array[i] = new Luck(l, t);
		}
		System.out.println(luckBalance(array, k));
    }
}

