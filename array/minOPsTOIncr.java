package array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: 1827. 最少操作使数组递增
 * @author: Qr
 * @create: 2021-09-15 10:27
 *
 * 给你一个整数数组nums（下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加1。
 *
 * 比方说，如果nums = [1,2,3]，你可以选择增加nums[1]得到nums = [1,3,3]。
 * 请你返回使 nums严格递增的 最少操作次数。
 *
 * 我们称数组nums是 严格递增的，当它满足对于所有的0 <= i < nums.length - 1都有nums[i] < nums[i+1]。一个长度为 1的数组是严格递增的一种特殊情况。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以进行如下操作：
 * 1) 增加 nums[2] ，数组变为 [1,1,2] 。
 * 2) 增加 nums[1] ，数组变为 [1,2,2] 。
 * 3) 增加 nums[2] ，数组变为 [1,2,3] 。
 * 示例 2：
 *
 * 输入：nums = [1,5,2,4,1]
 * 输出：14
 * 示例 3：
 *
 * 输入：nums = [8]
 * 输出：0
 **/
public class minOPsTOIncr {
    //操作：只能+1, 变为严格升序 遍历数组,当前元素<前一个元素 则置当前元素 = 前一个元素+1
    public int minOperations(int[] nums) {
        //特判
        if(nums.length == 0 || nums.length == 1){
            return 0;
        }
        int minOps = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i-1]){
                int originVal = nums[i];
                nums[i] = nums[i-1] + 1;
                int ops = nums[i] - originVal;
                minOps += ops;
            }
        }
        return minOps;
    }


    // 升级版: 操作从只能+1, 变为能+1, -1, 变换后的数组从严格升序变成非严格升序
    /*
    可以观察出来最终变换后数组中的每个元素都是 >=原数组最小元素  且 <= 原数组最大元素
    根据这条性质 + 最少操作次数 想到带记忆的穷举, 即动态规划
    DP(i,j): 前i个元素的最少操作次数, nums[i] == j
    最终值： MIN{ dp(N,j)  j = 原数组最小值...原数组最大值 }
    初始值:  DP(1, j) = abs(j-nums[0])
    迭代：   DP(i, j) = MIN{ dp(i-1,k)  原数组最小值 <= k <= j   +   abs(nums[i] - j) }
            非严格升序所以 nums[i-1] <= nums[i] 即<=j, 所以k<=j,
     */

    public static int minOperationsAdvanced(int[] nums) {
        //特判
        if(nums.length == 0 || nums.length == 1){
            return 0;
        }
        int n = nums.length;
        int minOps = Integer.MAX_VALUE;
        int maxVal = nums[0];
        int minVal = nums[0];
        //1.找出原数组最大值和最小值
        for (int i = 1; i < n; i++) {
            maxVal = Math.max(maxVal,nums[i]);
            minVal = Math.min(minVal,nums[i]);
        }
        //构建dp数组 n * m
        int m = maxVal - minVal + 1;
        int[][] dp = new int[n][m];
        //初始化
        for (int j = 0; j < m; j++) {
            dp[0][j] = Math.abs(j+minVal - nums[0]);
        }
        for (int i = 1; i < n; i++) {
            int prevMin = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                //DP(i, j) = MIN{ dp(i-1,k)  原数组最小值 <= k <= j   +   abs(nums[i] - j) }
                /*
                这里可以不用三重循环来做, 这里做的相当于遍历当前行时, 取出上一行的列坐标<=自己的元素
                可以直接用一个额外的变量 prevMin 记录 最小的dp[i-1][k]
                int currMin = ;
                for (int k = 0; k <= j; k++) {
                    currMin = Math.min(currMin, dp[i-1][k] + Math.abs(nums[i] - j - minVal));
                }*/
                prevMin = Math.min(prevMin,dp[i-1][j]);
                int currMin = Integer.MAX_VALUE;
                currMin = Math.min(currMin,prevMin + Math.abs(nums[i] - j - minVal));
                dp[i][j] = currMin;
            }
        }
        //最终值: minOps = MIN{ dp(N,j)  j = 原数组最小值...原数组最大值 }
        for (int j = 0; j < m; j++) {
            minOps = Math.min(minOps,dp[n-1][j]);
        }
        return minOps;
    }

    //第二种方法： slope trick, 没太理解, 基本操作就是用最大堆记录当前最大元素, 累加当前元素 - 最大元素，同时需要注意当累加即变换时需要把最大堆出掉
    public static int  minOperationsHeap(int[] nums){
        //特判
        if(nums.length == 0 || nums.length == 1){
            return 0;
        }
        int minOps = 0;
        int dif = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            if (!maxHeap.isEmpty() && maxHeap.peek() > nums[i]){
                dif = maxHeap.poll() - nums[i];
                minOps += dif;
                maxHeap.add(nums[i]);
            }
            maxHeap.add(nums[i]);
        }
        return minOps;
    }

    public static void main(String[] args) {
        int[] nums = { 9847, 3752, 5621, 7012, 1986, 3090, 1383, 6257, 9501, 7004, 6181, 9387, 9137, 9305, 7795, 9310,
            3904, 8328, 6656, 8123, 1796, 2754, 4372, 5200, 3893, 8568, 4436, 3973, 8498, 1879, 2731, 4651, 4388,
            453, 2465, 2669, 6384, 819, 8565, 1952, 7219, 4362, 3012, 9380, 2645, 4800, 2945, 5778, 1993, 1170,
            1356, 8557, 1497, 8921, 670, 5155, 9115, 1095, 9400, 9451, 9344, 4393, 4201, 8167, 8129, 2004, 8839,
            1457, 7682, 1881, 9266, 6366, 9889, 242, 5318, 5248, 3670, 7381, 6567, 2317, 2162, 6670, 5876, 1179,
            2482, 9270, 4326, 4166, 6122, 2016, 3008, 5349, 1723, 5816, 4030};
        System.out.println(minOperationsHeap(nums));
        System.out.println(minOperationsAdvanced(nums));
    }

}
