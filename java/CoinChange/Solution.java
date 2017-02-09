import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	static long[] checked;

	static long changeWays(int n, int[] coins){
		int m = coins.length;
		long[][] t = new long[m+1][n+1];
		for (int i = 0; i <= m; i++){
			t[i][0] = 1;
		}
		Arrays.sort(coins);
		for (int i = 1; i <= m; i++) {
			int coin = coins[i - 1];
			for (int j = 1; j <= n; j++) {
				t[i][j] = t[i-1][j];
				if (j >= coin) {
					t[i][j] += t[i][j - coin];
				}
			}
		}
		return t[m][n];
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
		checked = new long[n+1];
		checked[0] = 1L;
		for (int i = 1; i <= n; i++){
			checked[i] = -1L;
		}

        int m = in.nextInt();
		int[] coins = new int[m];
		for (int i = 0; i < m; i++){ 
			coins[i] = in.nextInt();
		}
		System.out.println(changeWays(n, coins));
    }
}

