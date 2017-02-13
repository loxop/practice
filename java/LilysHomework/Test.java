public class Test {
	static int reversedBinarySearch(int[] arr, int k){
		int i = 0;
		int s = 0;
		int e = arr.length;
		int h = e + s / 2;
		while(arr[h] != k && e > s && s >= 0 && e <= arr.length - 1){
			if (k < h){
				s = h + 1;
			} else {
				e = h - 1;
			}
			h = e + s / 2;
		}
		return h;
	}
	public static void main(String[] args){
		int[] a = new int[]{6,5,4,3,2,1};
		int[] b = new int[]{10,9,8,7,6};
		System.out.println("" + reversedBinarySearch(a, 5));
		System.out.println("" + reversedBinarySearch(b, 7));
	}
}
