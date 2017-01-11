public class StockTrading {
	static int get_max_profit(int prices[]) {
		int min = Integer.MAX_VALUE;
		int max = -1;
		for (int i = 0; i < prices.length; i++) {
			max = Math.max(prices[i] - min, max);
			min = Math.min(prices[i], min);
		}
		return max;

	}

	public static void main (String[] args){
		int[] prices = new int[args.length];
		for(int i = 0; i < args.length; i++){
			prices[i] = Integer.parseInt(args[i]);
		}
		System.out.println(get_max_profit(prices));
	}
}
