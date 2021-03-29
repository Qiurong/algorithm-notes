package dynamic_programming;

/**
 * @description: 53. 最大子序和
 * @author: Qr
 * @create: 2021-03-29 11:10
 **/
public class maximum_subarray {
    public int maxSubArray(int[] nums) {
        int len = nums.length - 1;
        int maxSum = nums[0];
        int currMax[] = new int[len];
        currMax[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (currMax[i-1] < 0){
                currMax[i] = nums[i];
            }else {
                currMax[i] = currMax[i-1] + nums[i];
            }
            maxSum = Math.max(maxSum,currMax[i]);
        }
        return maxSum;
    }
}
