import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static boolean sherlock(int[] a){
        int n = a.length;
        if (n == 1){
            return true;
        }
        int left = 0;
        int right = 0;
        for (int i: a){
            right += i;
        }
        for (int i = 0; i < n; i++){
            if (i > 0){
                left += a[i-1];
            }
            right -= a[i];
            if (left == right){
                return true;
            }
            if (left > right){
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i < t; i++){
            int n = in.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }
            System.out.println(sherlock(a)?"YES":"NO");
        }
    }
}
