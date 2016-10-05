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

	static int fastTernarySearch(char[] haystack, char needle, char[][] distribution){
		int index = -1;
		return index;
	}

	public static void main(String[] args){
		if (args.length < 2){
			System.out.println("Usage1: java TernarySearch STRING_FOR_DATA_ARR CHAR_TO_FIND");
			System.out.println("\t* the STRING should be sorted.");
			System.out.println("ex) java TernarySearch abklmxyz x");
			System.out.println("\nUsage2: java TernarySearch STRING_FOR_DATA_ARR CHAR_TO_FIND DATA_DISTRIBUTION [TEST_REPS]");
			System.out.println("\t* the DATA_DISTRIBUTION is comma-separated string which contains previous STRINGs.");
			System.out.format("\t* if TEST_REPS is given, comparison(naive search vs. with distribution) will be done with TEST_REPS times, otherwise default value (%d) will be used.\n", DEFAULT_TEST_REPS);
			System.out.println("ex) java TernarySearch abklmxyz x abcdefg,vwxyz,abcxyz\n");
			return;
		}

		char[] array = args[0].toCharArray();
		char ch = args[1].charAt(0);
		
		int index;
		if (args.length > 2){
			String[] dist_str = args[2].split(",");
			char[][] dist_arr = new char[dist_str.length][];
			for (int i = 0; i < dist_str.length; i++){
				dist_arr[i] = dist_str.toCharArray();
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
