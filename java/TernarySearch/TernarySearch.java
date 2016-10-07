import java.util.Map;
import java.util.HashMap;

class TernarySearch {
	public static final int DEFAULT_TEST_REPS = 10000;

	static int simpleTernarySearch(char[] haystack, char needle){
		int s = 0;
		int e = haystack.length - 1;
		int fp, sp;
		int index = -1;
		while(s <= e){
			fp = s + (e - s) / 3;
			sp = s + (e - s) * 2 / 3;
			// System.out.format("%d%c %d%c %d%c %d%c\n", s, haystack[s],fp, haystack[fp], sp, haystack[sp], e, haystack[e]);
			if (haystack[fp] == needle){
				index = fp;
				break;
			} else if (haystack[sp] == needle){
				index = sp;
				break;
			} else if (needle < haystack[fp]){
				e = fp - 1;
			} else if (needle < haystack[sp]){
				s = fp + 1;
				e = sp - 1;
			} else {
				s = sp + 1;
			}
		}
		return index;
	}

	static int fastTernarySearch(char[] haystack, char needle, Map<Character, Double> distribution){
		int index = -1;

		return index;
	}

	static Map<Character, Double> calculateDistribution(char[][] data_history){
		Map<Character, Double> distributionMap = new HashMap<Character, Double>();
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();

		int len;
		char ch;
		Double sum;
		Double position;
		
		for (char[] record : data_history){
			len = record.length;
			for (int i = 0; i < len; i++){
				ch = record[i];
				position = i * 1.0 / len;
				sum = distributionMap.get(ch);
				if (sum == null){
					distributionMap.put(ch, position);
					countMap.put(ch, 1);
				} else {
					distributionMap.put(ch, sum + position);
					countMap.put(ch, countMap.get(ch) + 1);
				}
			}
		}

		for (Character key : distributionMap.keySet()){
			distributionMap.put(key, distributionMap.get(key) / countMap.get(key));
		}

		return distributionMap;
	}

	static void compare(char[] haystack, char needle, Map<Character, Double> distribution){
		
	}

	public static void main(String[] args){
		if (args.length < 2){
			System.out.println("Usage1: naive ternary search");
			System.out.println("\njava TernarySearch STRING_FOR_DATA_ARR CHAR_TO_FIND");
			System.out.println("\t* the STRING should be sorted.");
			System.out.println("ex) java TernarySearch abklmxyz x");
			System.out.println("\nUsage2: fast ternary search with given data distribution");
			System.out.println("\njava TernarySearch STRING_FOR_DATA_ARR CHAR_TO_FIND DATA_DISTRIBUTION");
			System.out.println("\t* the DATA_DISTRIBUTION is comma-separated string which contains previous STRINGs.");
			System.out.println("ex) java TernarySearch abklmxyz x abcdefg,vwxyz,abcxyz");
			System.out.println("\nUsage3: comparison (naive ternary search vs. fast ternary search with given data distribution)");
			System.out.println("\njava TernarySearch STRING_FOR_DATA_ARR CHAR_TO_FIND DATA_DISTRIBUTION TEST_REPS");
			System.out.println("\t* repeat both ternary searches for TEST_REPS times, and compare elapsed time");
			System.out.println("ex) java TernarySearch abklmxyz x abcdefg,vwxyz,abcxyz 10000");
			return;
		}

		char[] array = args[0].toCharArray();
		char ch = args[1].charAt(0);
		
		int index;
		if (args.length > 2){
			String[] dist_str = args[2].split(",");
			char[][] dist_arr = new char[dist_str.length][];
			for (int i = 0; i < dist_str.length; i++){
				dist_arr[i] = dist_str[i].toCharArray();
			}
			Map<Character, Double> distribution = calculateDistribution(dist_arr);

			if (args.length > 3) {

			} else {
				index = fastTernarySearch(array, ch, distribution);
				if (index < 0){
					System.out.format("%c not found", ch);
				} else {
					System.out.format("%c is at %d\n", ch, index + 1);
				}
			}
		} else {
			index = simpleTernarySearch(array, ch);
			if (index < 0){
				System.out.format("%c not found", ch);
			} else {
				System.out.format("%c is at %d\n", ch, index + 1);
			}
		}
		
	}
}
