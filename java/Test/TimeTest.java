class TimeTest {
	public static void main(String[] args){
		String str = "Hello";
		char c = 'e';
		long start = System.nanoTime();
		if (str.charAt(1) == c){
			c = 'd';
		}
		System.out.format("%d\n", System.nanoTime() - start);
	}
}
