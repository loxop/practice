import java.util.Arrays;

class TernarySearch {
	public static final int DEFAULT_TEST_REPS = 10000;

	static int simpleTernarySearch(char[] haystack, char needle){
		int s = 0;
		int e = haystack.length - 1;
		int fp, sp;
		int index = -1;
		// System.out.format("\n%c\n", needle);
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

	static int fastTernarySearch(char[] haystack, char needle, double average_position){
		if (average_position < 0){
			return simpleTernarySearch(haystack, needle);
		}
		int s = 0;
		int e = haystack.length - 1;
		int fp, sp;
		int index = -1;
		int supposed_index = (int)(e * average_position);
		// System.out.format("\n%c: %f,%d\n", needle, average_position, supposed_index);
		while(s <= e){
			fp = s + (e - s) / 3;
			sp = s + (e - s) * 2 / 3;

			// If supposed_index is still in valid range, narrow the section in which the average_position belongs to
			if (supposed_index >= s && supposed_index <= e){
				fp -= (fp - supposed_index) / 2;
				sp -= (sp - supposed_index) / 2;
			}

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

	static double calculateAveragePosition(char[][] data_history, char ch){
		int len;
		int index;
		double sum = 0.0;
		int count = 0;
		for (char[] record : data_history){
			index = -1;
			len = record.length;
			for (int i = 0; i < len; i++){
				if (record[i] == ch){
					index = i;
					break;
				}
			}
			if (index >= 0){
				sum += 1.0 * index / len;
				count++;
			}
		}

		if (count == 0){
			return -1.0;
		}
		return sum / count;
	}

	static void compare(char[] array, char[][] data_history, int reps){
		int index;
		long naive_start = System.nanoTime();
		for (int i = 0; i < reps; i++){
			for (int j = 0; j < array.length; j++){
				index = simpleTernarySearch(array, array[j]);
			}
		}
		long naive_elapsed = System.nanoTime() - naive_start;

		// prepare all average_positions for all characters in array
		double[] avg_pos_array = new double[array.length];
		for (int i = 0; i < array.length; i++){
			avg_pos_array[i] = calculateAveragePosition(data_history, array[i]);
		}

		long fast_start = System.nanoTime();
		for (int i = 0; i < reps; i++){
			for (int j = 0; j < array.length; j++){
				index = fastTernarySearch(array, array[j], avg_pos_array[j]);
			}
		}
		long fast_elapsed = System.nanoTime() - fast_start;

		System.out.format("Naive: %dns\n", naive_elapsed);
		System.out.format("Fast:  %dns\n", fast_elapsed);
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
			double avg_position = calculateAveragePosition(dist_arr, ch);
			index = fastTernarySearch(array, ch, avg_position);
			if (index < 0){
				System.out.format("%c not found\n", ch);
			} else {
				System.out.format("%c is at %d\n", ch, index + 1);
			}
			if (args.length > 3) {
				compare(array, dist_arr, Integer.parseInt(args[3]));
			}
		} else {
			index = simpleTernarySearch(array, ch);
			if (index < 0){
				System.out.format("%c not found\n", ch);
			} else {
				System.out.format("%c is at %d\n", ch, index + 1);
			}
		}
		
	}
}
