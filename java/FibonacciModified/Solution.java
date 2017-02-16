import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static BigInteger first;
    static BigInteger second;
    static Map<Integer, BigInteger> prevFibo;
    
    static BigInteger fibo(int n){
        if (n == 1){
            return first;
        }
        if (n == 2){
            return second;
        }
        if (prevFibo.containsKey(n)){
            return prevFibo.get(n);
        }
        BigInteger fn2 = fibo(n-2);
        BigInteger fn1 = fibo(n-1);
        BigInteger fn = fn2.add(fn1.multiply(fn1));
        prevFibo.put(n, fn);
        return fn;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String f = in.next();
        String s = in.next();
        int n = in.nextInt();
        
        first = new BigInteger(f);
        second = new BigInteger(s);
        prevFibo = new HashMap<Integer, BigInteger>();
        System.out.println(fibo(n));
    }
}
