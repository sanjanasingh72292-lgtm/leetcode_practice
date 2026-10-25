class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZero = 0;

        for (int curr = 0; curr < nums.length; curr++) {
            if (nums[curr] != 0) {
                int temp = nums[lastNonZero];
                nums[lastNonZero] = nums[curr];
                nums[curr] = temp;
                lastNonZero++;
            }
        }
    }
}