package dynamic_programming;

/**
 * @description: 53. 最大子序和
 * @author: Qr
 * @create: 2021-03-29 11:10
 **/
public class maximum_subarray {
    //时间复杂度:O(n), 空间复杂度:O(n)
    public int maxSubArray(int[] nums) {
        int len = nums.length;
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

    //由于dp计算过程中dp[i]只与dp[i-1]有关，可以用滚动数组思想把时间复杂度降到O(1)
    public int maxSubArray_WithLessSpace(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];
        int preMax = nums[0];
        //int currMax = nums[0];
        for (int i = 1; i < len; i++) {
            if (preMax  < 0){
                preMax = nums[i];
            }else {
                preMax += nums[i];
            }
            maxSum = Math.max(maxSum,preMax);
        }
        return maxSum;
    }
}
