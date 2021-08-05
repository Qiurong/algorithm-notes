package interview_questions;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * @description: 718. 最长重复子数组
 * @author: Qr
 * @create: 2021-08-04 16:59
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *
 **/
public class maximum_length_of_repeated_subarray {
    //遍历nums1, 枚举每个nums1[i]为开始的数组  和nums2的  最大公共子数组
    public int findLength_brutalForce(int[] nums1, int[] nums2) {
        int maxlen = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]){
                    int m = i;
                    int n = j;
                    //计算以i 和 j开始的最大子数组
                    while (m < nums1.length && n < nums2.length && nums1[m] == nums2[n]){
                        m++;
                        n++;
                    }
                    maxlen = Math.max(maxlen,m-i);
                }
            }
        }
        return maxlen;
    }

    //动态规划优化：  记录a[i]与b[j]的最长公共前缀为dp[i][j]
    //              0                     a[i] != b[j]
    // dp[i][j] =
    //              dp[i+1][j+1] + 1      a[i] == b[j]
    //这里有个为了防止dp数组越界的方法，就是初始化为 (m+1) * (n+1)
    public int findLength_dp(int[] nums1,int[] nums2){
        int maxLen = 0;
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (nums1[i] == nums2[j]){
                    dp[i][j] = dp[i+1][j+1] +1;
                    maxLen = Math.max(maxLen,dp[i][j]);
                }
            }
        }
        return maxLen;
    }

    //滑动窗口
    //固定 两个数组中比较长的那个数组不动, 不然会出现下标越界问题
    //a不动, b不断的滑动, 计算两者相交部分最大的公共子数组长度, 最后其中最大的就是两个数组最大的公共子数组
    public int findLength_slidingWindow(int[] nums1,int[] nums2){
        return nums1.length >= nums2.length ? findLen(nums1,nums2) : findLen(nums2,nums1);
    }

    //a是较长数组
    public int findLen(int[] a, int[] b){
        int maxlen = 0;
        int an = a.length;
        int bn = b.length;

        //1.b的队尾对其a的队头 ----> b的队头对其a的队头   bn次
        for (int len = 1; len <= bn; len++) {
            //bStart从队尾最后一个元素到0
            maxlen = Math.max(maxlen,findMaxLenWithLen(a,b,0,bn-len,len));
        }
        //2.b的队头对其a的队头 ----> b的队尾对其a的队尾   an-bn次
        for (int i = 1; i <= an - bn; i++) {
            maxlen = Math.max(maxlen,findMaxLenWithLen(a,b,i,0,bn));
        }
        //3.b的队尾对其a的队尾 ----> b的队头对其a的队尾    移动bn-1次
        for (int len = bn - 1; len >= 1; len--) {
            maxlen = Math.max(maxlen,findMaxLenWithLen(a,b,an-len,0,len));
        }
        return maxlen;
    }

    /**
     * 返回两个数组 从指定下标开始 长度为len 的两个子数组的最长公共子数组
     * @param a
     * @param b
     * @param aStart
     * @param bStart
     * @param len
     * @return
     */
    public int findMaxLenWithLen(int[] a, int[] b, int aStart, int bStart, int len){
        int max = 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (a[aStart+i] == b[bStart+i]){
                count++;
                max = Math.max(max,count);
            }else {
                //count归零
                count = 0;
            }
        }
        return max;
    }
}
