import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static boolean[] result; // true: First, false: Second
    static void makeArray(){
        result[1] = false;
        result[2] = true;
        result[3] = true;
        result[4] = true;
        result[5] = true;
        for (int i = 6; i <= 100; i++){
            if (result[i-2] && result[i-3] && result[i-5]){
                result[i] = false;
            } else {
                result[i] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        result = new boolean[101];
        makeArray();
        for (int i = 0; i < T; i++){
            int n = in.nextInt();
            System.out.println(result[n]?"First":"Second");
        }
    }
}
