import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static boolean checkCondition(int i, int[] arr, boolean asc){
        int n = arr.length;
        if (asc){
            return (i > 0 && arr[i] < arr[i-1]) || (i < n - 1 && arr[i] > arr[i+1]);
        }
        return (i > 0 && arr[i] > arr[i-1]) || (i < n - 1 && arr[i] < arr[i+1]);
    }

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int n = in.nextInt();
        int[] arr = new int[n];
        int diff = 0;
        for (int i = 0; i < n; i++){
			if (in.hasNextInt()){
            	arr[i] = in.nextInt();
			}
            if (i > 0){
                diff += arr[i] - arr[i-1];
            }
        }
        boolean asc = true;
        if (diff < 0){
            asc = false;
        }
        boolean swapped = true;
        int swapCount = 0;
        while(swapped){
            int minToSwap = Integer.MAX_VALUE;
            int minToSwapIndex = -1;
            int maxToSwap = -1;
            int maxToSwapIndex = -1;
            for (int i = 0; i < n; i++){
                if (checkCondition(i, arr, asc)) {
                    if (arr[i] < minToSwap){
                        minToSwap = arr[i];
                        minToSwapIndex = i;
                    }
                    if (arr[i] > maxToSwap) {
                        maxToSwap = arr[i];
                        maxToSwapIndex = i;
                    }
                }
            }
			System.out.format("min: %d(#%d), max: %d(#%d)\n", minToSwap, minToSwapIndex, maxToSwap, maxToSwapIndex);
            if (minToSwapIndex < 0 && maxToSwapIndex < 0) {
                swapped = false;
            } else {
                int t = arr[minToSwapIndex];
                arr[minToSwapIndex] = arr[maxToSwapIndex];
                arr[maxToSwapIndex] = t;
                swapCount++;
            }
        }
        System.out.println(swapCount);
    }
}
