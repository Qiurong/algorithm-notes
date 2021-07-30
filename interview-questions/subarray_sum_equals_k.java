package interview_questions;

import java.util.Arrays;

/**
 * @description: 560. 和为K的子数组
 * @author: Qr
 * @create: 2021-07-30 17:09
 *
 * 给定一个整数数组和一个整数k，你需要找到该数组中和为k的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 *
 **/
public class subarray_sum_equals_k {
    //滑动窗口, 存在负数不可行
    public static int subarraySum_window(int[] nums, int k) {
        int n = 0;
        int left = 0;
        int right = 0;
        int winSum = nums[0];
        //窗口滑动
        while (left <= right && right < nums.length){
            if (winSum == k){
                n++;
                winSum -= nums[left];
                left++;
                if (left > right){
                    right++;
                    if (right == nums.length){
                        break;
                    }
                    winSum += nums[right];
                }
            }else if (winSum < k){
                //right++
                right++;
                if (right == nums.length){
                    break;
                }
                winSum += nums[right];
            }else {
                //left++
                winSum -= nums[left];
                left++;
                if (left > right){
                    right++;
                    if (right == nums.length){
                        break;
                    }
                    winSum += nums[right];
                }
            }
        }
        return n;
    }

    public int subarraySum(int[] nums, int k) {
        int n = 0;
        int subSum = 0;
        //暴力: 以i位开头，然后一直到尾部的子数组的和
        for (int i = 0; i < nums.length; i++) {
            //subSum恢复为0
            subSum = 0;
            for (int j = i; j < nums.length; j++) {
                subSum += nums[j];
                if (subSum == k){
                    n++;
                }
            }
        }
        return n;
    }
}
