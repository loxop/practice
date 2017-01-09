public class PrependTest {
	public static final int REPEAT = 10000;
	public static void main(String[] args){
		long start, end;
		String base = "";
		String prefix = "asdf";
		start = System.nanoTime();
		for (int i = 0; i < REPEAT; i++){
			base = prefix + base;
		}
		end = System.nanoTime();
		System.out.println((end - start) + "ns / String");

		StringBuilder sbuilder = new StringBuilder();
		start = System.nanoTime();
		for (int i = 0; i < REPEAT; i++) {
			sbuilder.insert(0, prefix);
		}
		end = System.nanoTime();
		System.out.println((end - start) + "ns / StringBuilder");

		StringBuffer sbuffer = new StringBuffer();
		start = System.nanoTime();
		for (int i = 0; i < REPEAT; i++) {
			sbuffer.insert(0, prefix);
		}
		end = System.nanoTime();
		System.out.println((end - start) + "ns / StringBuffer");
	}
}
