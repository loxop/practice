public class ReferenceTest {
	static void swap(int[] a, int[] b){
		int[] t = a;
		a = b;
		b = t;
	}
	public static void main(String[] args){
		int[] a = {1, 2, 3};
		int[] b = {9, 10, 11};
		swap(a, b);
		System.out.println(a[0]);
	}
}
