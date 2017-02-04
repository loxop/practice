import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	static boolean isValid(char[][] g){
		for (int i = 0; i < g.length; i++) {
			Arrays.sort(g[i]);
		}
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j <g[i].length - 1; j++) {
				if (g[j][i] > g[j + 1][i]){
					return false;
				}
			}
		}
		return true;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            int n = in.nextInt();
			char[][] grid = new char[n][];
			for (int i = 0; i < n; i++) {
				String line = in.next();
				grid[i] = line.toCharArray();
			}
			System.out.println(isValid(grid)?"YES": "NO");
        }
    }
}

