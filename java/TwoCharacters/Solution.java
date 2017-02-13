import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        
        Set<Character> charset = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++){
            charset.add(s.charAt(i));
        }
        Character[] charArr = charset.toArray(new Character[0]);
        int maxLength = 0;
        for (int i = 0; i < charArr.length - 1; i++){
            for (int j = i+1; j < charArr.length; j++) {
            	int count = 0;
                char prev = ' ';
                char a = charArr[i];
                char b = charArr[j];
                for (int k = 0; k < s.length(); k++) {
                    char c = s.charAt(k);
                    if (c != a && c!= b){
                        continue;
                    }
                    if (c != prev) {
                        prev = c;
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count > maxLength){
                    maxLength = count;
                }
            }
        }
        System.out.println(maxLength);
    }
}

