package array;

import java.util.HashMap;

/**
 * @description: 560. 和为 K 的子数组
 * @author: Qr
 * @create: 2021-09-08 15:15
 *
 *
给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。



示例 1：

输入：nums = [1,1,1], k = 2
输出：2

示例 2：
输入：nums = [1,2,3], k = 3
输出：2
 **/
public class subarraySum_k {
    //思路： dp[i][j]记录 [i,..j]子数组的和
    //时空复杂度都是 O(n^2), 超出空间复杂度
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //先计算[i..j]子数组的和
                if (i == j){
                    dp[i][j] = nums[i];
                }else {
                    dp[i][j] = dp[i][j-1] + nums[j];
                }
                if (dp[i][j] == k){
                    num++;
                }
            }
        }
        return num;
    }

    //优化空间复杂度, 可以观察到其实dp[i][j]的取值只跟dp[i][j-1]有关, 优化空间复杂度为O(1)
    //时间复杂度：O(n^2), 空间：0(1)
    public int subarraySum_SpaceOptimized(int[] nums, int k) {
        int prevSum = 0;
        int currSum = 0;
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j){
                    currSum = nums[i];
                }else {
                    currSum = prevSum + nums[j];
                }
                //判断
                if (currSum == k){
                    num++;
                }
                //更新迭代值
                prevSum = currSum;
            }
        }
        return num;
    }

    //前缀和. preSum[i] = nums前i位的和即[0..i-1] preSum[0] = 0, preSum[1] = nums[0]
    // nums[i..j] = preSum[j+1] - preSum[i]
    //空间复杂度:O(N)  时间复杂度：O(N^2)
    public int subarraySum_PreSum(int[] nums, int k){
        int num = 0;
        int n = nums.length;
        //1.计算前缀和数组
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        //2.根据前缀和计算连续子数组出现次数
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //nums[i..j] = preSum[j+1] - preSum[i]
                int subSum = preSum[j+1] - preSum[i];
                if (subSum == k){
                    num++;
                }
            }
        }
        return num;
    }

    //加入HashMap进行优化， 寻找num[i,,j]  preSum[i] = num[0..i]
    //仿照两数之和的思想, 转 a + b == k 为 b == k - a, 将b提前用hashset存好, 那么获取b的时间复杂度就变成了O(1)
    //转 preSum[j] - preSum[i-1] == k, 为 preSum[i-1] == preSum[j] - k
    //其思想主要在于 能O(1) 时间 获得 preSum[i-1] == preSum[j] - k 的个数
    //跟两数之和一样，  1.先存：即把preSum[i](i < j)存在hashMap中
    //               2.获取: 获取出 preSum[i] == preSum[j] - k (i < j)的 preSum的个数
    public int subarraySum_PreSumOptimized(int[] nums, int k){
        int num = 0;
        int n = nums.length;
        int preSum = 0;
        //k: preSum的值  v:这个值的出现次数
        HashMap<Integer,Integer> map = new HashMap<>();
        //初始化,
        map.put(0,1);
        //在计算preSum的同时, 把其<val,count>键值对放入
        for (int j = 0; j < n; j++) {
            //1.算出preSum[j]
            preSum += nums[j];
            //2.找是否存在preSum[i] == preSum - k, 其中i严格小于j； 存在则获取其个数
            if (map.containsKey(preSum - k)){
                num += map.get(preSum - k);
            }
            //3.把当前preSum的值放入map中
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return num;
    }

}
