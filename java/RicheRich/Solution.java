import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	static String richeRich(String n, int k){
		int len = n.length();
		int l = 0;
		for (int i = 0 ; i < len / 2; i++) 
		{
			int a = n.charAt(i) - '0';
			int b = n.charAt(len - 1 - i) - '0';
			if (a != b){
				l++;
			}
		}
		// here, l is the number of different digits
		if (k < l){
			return "-1";
		}

		// here, k >= l
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0 ; i < len / 2; i++) 
		{
			int j = len - 1 - i; // index of mirror position

			int a = n.charAt(i) - '0';
			int b = n.charAt(j) - '0';
			if (a == b) {
				if (k - l >= 2 && a != 9) {
					sb.setCharAt(i, '9');
					sb.setCharAt(j, '9');
					k -= 2;
				}
			} else {
				if (a == 9 || b == 9 || k == l) {
					if (a > b){
						sb.setCharAt(j, (char)(a + '0'));
					} else {
						sb.setCharAt(i, (char)(b + '0'));
					}
					k--;
				} else {
					// k > l, that means k can be used one more
					sb.setCharAt(i, '9');
					sb.setCharAt(j, '9');
					k -= 2;
				}
				l--;
			}
		}
		if (k > 0 && (len % 2 == 1)) {
			sb.setCharAt(len / 2, '9');
		}
		return sb.toString();
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String number = in.next();
		System.out.println(richeRich(number, k));
    }
}

