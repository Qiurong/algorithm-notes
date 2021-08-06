package interview_questions;

/**
 * @description: 300. 最长递增子序列
 * @author: Qr
 * @create: 2021-08-06 14:16
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 **/
public class longest_increasing_subsequence {
    //递增子序列长度的本质是：a[i]之后有几个大于自己的元素
    //从后向前扫描, 每到一个元素下标a[i], 寻找其之后大于自己的最小元素, dp[i] = dp[j]+1。
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int maxLen = 1;
        int[] dp = new int[nums.length];
        //设置初值
        dp[nums.length-1] = 1;
        //从倒数第二元素从后向前遍历
        for (int i = nums.length-2; i >= 0; i--) {
            //1.赋初值为1
            dp[i] = 1;
            //2.寻找当前元素后大于它的元素中最大的dp值  并更新
            int maxDp = 0;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] > nums[i]){
                    maxDp = Math.max(maxDp,dp[j]);
                }
            }
            dp[i] += maxDp;
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }
}
