public class NonDecreasingSequence {
	public static void main(String[] args) {
		int[] num = {5, 3, 4, 8, 9, 10, 6, 7, 8, 9};
		int n = find_seq(num);
		System.out.println(n);
	}

	static int find_seq(int []num){
		int n = num.length;
		int len[] = new int[n];
		int from[] = new int[n];
		int max_n = -1;
		int max_i = -1;
		for (int i = 0; i < n; i++) {
			len[i] = 1;
			from[i]= -1;
			if (i > 0) {
				for (int j = i - 1; j >= 0; j--) {
					if ( (num[j] <= num[i]) && (len[j] + 1 > len[i])) {
						len[i] = len[j] + 1;
						from[i] = j;
					}
				}
			}
			if (len[i] > max_n) {
				max_n = len[i];
				max_i = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(num[max_i]);
		for (int i = from[max_i]; i > 0; i = from[i]) {
			sb.insert(0, num[i] + "->");
		}
		System.out.println(sb);
		return max_n;
	}
}
