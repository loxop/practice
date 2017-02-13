import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	static int reversedBinarySearch(int[] arr, int k){
		int i = 0;
		int s = 0;
		int e = arr.length;
		int h = e + s / 2;
		while(arr[h] != k && e > s){
			if (k < h){
				s = h + 1;
			} else {
				e = h - 1;
			}
			h = e + s / 2;
		}
		return h;
	}
    public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] org = new int[n];
        int[] org2 = new int[n];
        int[] sorted = new int[n];
        int[] reverse = new int[n];
		for (int i = 0; i < n; i++){
			int k = in.nextInt();
			org[i] = k;
			org2[i] = k;
			sorted[i] = k;
			reverse[n - 1 - i] = k;
		}
		Arrays.sort(sorted);

		boolean swapped = true;

		int s_asc = 0;
		while(swapped) {
			swapped = false;
			for (int i = 0; i < n; i++){
				if (org[i] != sorted[i]){
					int idx = Arrays.binarySearch(sorted, org[i]);
					int t = org[i];
					org[i] = org[idx];
					org[idx] = t;
					swapped = true;
					s_asc++;
				}
			}
		}

		swapped = true;
		int s_desc = 0;
		int[] rev = new int[n];
		for ( int i = 0; i < n; i++){
			rev[i] = sorted[n - 1 - i];
		}
		while(swapped) {
			swapped = false;
			for (int i = 0; i < n; i++){
				if (org[i] != sorted[i]){
					int idx = Arrays.binarySearch(sorted, org[i]);
					int t = org[i];
					org[i] = org[idx];
					org[idx] = t;
					swapped = true;
					s_desc++;
				}
			}
		}

		System.out.println(Math.min(s_asc, s_desc));
    }
}
