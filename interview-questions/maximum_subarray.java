package interview_questions;

/**
 * @description: 53. 最大子序和
 * @author: Qr
 * @create: 2021-09-01 19:54
 **/
public class maximum_subarray {
    //dp:  f(n): 以n为结尾的连续子数组的最大值
    // f(n) = MAX{ f(n-1) + nums[n], nums[n] }
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i-1] > 0){
                dp[i] = dp[i-1] + nums[i];
            }else {
                dp[i] = nums[i];
            }
            maxSum = Math.max(maxSum,dp[i]);
        }
        return maxSum;
    }

    //可用滚动思想优化到o(1)的时间复杂度
    public int maxSubArray_optimized(int[] nums){
        int maxSum = nums[0];
        int prevDp = nums[0];
        int currDp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prevDp < 0){
                currDp = nums[i];
            }else {
                currDp = prevDp + nums[i];
            }
            //更新prevDp
            prevDp = currDp;
            maxSum = Math.max(maxSum,currDp);
        }
        return maxSum;
    }
}
