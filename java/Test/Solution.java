import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static void rotate(int []a, int k){
        int len = a.length;
        int[] b = new int[len];
        int j = k % len;
        int count = 0;
        for(int i = j; i < len; i++){
            b[count++] = a[i];
        }
        for(int i = 0; i < j; i++){
            b[count++] = a[i];
        }
        for(int i = 0; i < len; i++){
            a[i] = b[i];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        rotate(a, k);
		for (int i: a){
			System.out.print(i + " ");
		}
		System.out.println();
        for(int a0 = 0; a0 < q; a0++){
            int m = in.nextInt();
            System.out.println(a[m]);
        }
    }
}

