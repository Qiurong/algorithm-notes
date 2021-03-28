package dynamic_programming;

/**
 * @description: 300. 最长递增子序列
 * @author: Qr
 * @create: 2021-03-17 22:42
 **/
public class longest_increasing_subsequence {
    //从后往前迭代, 算法错误.看了官方题解应该是从前往后迭代.
    public int lengthOfLIS_brutalForce(int[] nums) {
        int len = nums.length;
        //用于记录最长递增子序列的数值. dp[i] = nums[i...len-1]的最长递增子序列长度
        int dp[] = new int[len];
        //用于记录最长递增子序列的最小值. dp[i] = nums[i...len-1]的最长递增子序列的最小值
        int min[] = new int[len];
        //边界
        dp[len-1] = 1;
        min[len-1] = nums[len-1];
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < min[i+1]){
                dp[i] = dp[i+1] + 1;
                min[i] = nums[i];
            }
            else {
                boolean flag = false;
                for (int j = i+2; j <= len-1 ; j++) {
                    if (nums[i] < min[j]){
                        int temp = dp[j] + 1;
                        if (temp > dp[i+1]){
                            dp[i] = temp;
                            min[i] = nums[i];
                        } else if (temp < dp[i+1]){
                            dp[i] = dp[i+1];
                            min[i] = nums[i];
                        } else{
                            dp[i] = dp[i+1];
                            min[i] = Math.max(nums[i],nums[i]);
                        }
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    dp[i] = dp[i+1];
                    min[i] = min[i+1];
                }
            }
        }
        return dp[0];
    }
}
