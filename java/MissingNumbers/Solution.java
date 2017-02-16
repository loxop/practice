import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            a[i] = in.nextInt();
            if (a[i] < min){
                min = a[i];
            }
        }
        int[] c_a = new int[100];
        int[] c_b = new int[100];
        for (int i = 0; i < n; i++){
            c_a[a[i] - min]++;
        }

        int m = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++){
            b[i] = in.nextInt();
            c_b[b[i] - min]++;
        }
        
        for (int i = 0; i < 100; i++) {
            if (c_a[i] != c_b[i]){
                System.out.print(min + i);
                System.out.print(" ");
            }
        }
    }
}
