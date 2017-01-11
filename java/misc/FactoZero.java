public class FactoZero {
	static int factoZero(int n) {
		if (n < 5){
			return 0;
		}
		int ten = 0;
		int five = 0;
		int two = 0;
		for (int i = 1; i <= n; i++) {
			int m = i;
			while (m >= 10 && m % 10 == 0){
				m /= 10;
				ten++;
			}
			while (m >= 5 && m % 5 == 0){
				m /= 5;
				five++;
			}
			while (m >= 2 && m % 2 == 0){
				m /= 2;
				two++;
			}
		}
		return ten + Math.min(five,two);
	}

	public static void main(String[] args){
		int n = Integer.parseInt(args[0]);
		System.out.println("FactoZero of " + n + " is " + factoZero(n));
	}
}
