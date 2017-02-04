import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	static void swap(int[] q, int a, int b){
		int t = q[a];
		q[a] = q[b];
		q[b] = t;
	}

	static int bribes(int[] q){
		int count = 0;
		for(int i = q.length - 1; i >= 0; i--) {
			int n = q[i];
			int j = i + 1; // for simplicity
			if (j < n) { // that means n used bribes
				if (n - j > 2){
					return -1;
				}
				for (int k = i; k < n - 1; k++) {
					swap(q, k, k+1);
					count++;
				}
				i++;
			}
		}
		return count;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
            int q[] = new int[n];
            for(int q_i=0; q_i < n; q_i++){
                q[q_i] = in.nextInt();
            }
			int result = bribes(q);
			System.out.println(result<0?"Too chaotic":result);
        }
    }
}

