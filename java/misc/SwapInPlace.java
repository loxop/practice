class SwapInPlace {
	public static void main(String[] args){
		int[] nums = new int[2];
		nums[0] = Integer.parseInt(args[0]);
		nums[1] = Integer.parseInt(args[1]);
		System.out.println("Input: " + nums[0] + ", " + nums[1]);
		swapInPlace(nums);
		System.out.println("Swapp: " + nums[0] + ", " + nums[1]);
	}

	static void swapInPlace(int[] nums){
		nums[0] += nums[1];
		nums[1] = nums[0] - nums[1];
		nums[0] -= nums[1];
	}
}
