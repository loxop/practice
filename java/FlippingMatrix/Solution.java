import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int num_q = 0; num_q < q; num_q++){
            int n = in.nextInt();
            int[][] matrix = new int[2 * n][2 * n];
            for (int i = 0; i < 2 * n; i++){
                for (int j = 0; j < 2 * n; j++) {
                    matrix[i][j] = in.nextInt();   
                }
            }
            
            int m = 2 * n - 1;
            int max_sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int[] sub = new int[4];
                    sub[0] = matrix[i][j];
                    sub[1] = matrix[i][m - j];
                    sub[2] = matrix[m - i][j];
                    sub[3] = matrix[m - i][m - j];
                    int max_ij = sub[3];
                    for (int k = 0; k < 3; k++){
                        if (sub[k] > max_ij){
                            max_ij = sub[k];
                        }
                    }
                    max_sum += max_ij;
                }
            }
            System.out.println(max_sum);
        }
    }
}
