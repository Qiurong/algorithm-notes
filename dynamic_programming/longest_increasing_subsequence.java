package dynamic_programming;

/**
 * @description: 300. 最长递增子序列
 * @author: Qr
 * @create: 2021-03-17 22:42
 **/
public class longest_increasing_subsequence {
    //从前往后迭代
    //时间复杂度: O(n^2), 空间复杂度：O(n)
    public int lengthOfLIS_brutalForce(int[] nums) {
        int len = nums.length;
        int maxlen = 1;
        //用于记录最长递增子序列的数值. dp[i] = nums[i...len-1]的最长递增子序列长度.
        // 这个子序列必须包含nums[i]! (满足了动态规划 无后效性 的设计思想)
        int dp[] = new int[len];
        //初始化
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            //在这里给dp[i]赋初值以应对
            dp[i] = 1;
            //dp[i] = max{dp[j]+1} if nums[j] < nums[i]
            for (int j = 0; j <= i; j++) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlen = Math.max(dp[i], maxlen);
        }
        return maxlen;
    }
}
