package interview_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 15. 三数之和
 * @author: Qr
 * @create: 2021-09-01 19:08
 **/
public class three_Sum {
    //基本思想：先排序，然后两重循环算出两数之和，同时第三个数字从右向左, 其实跟两数之和很像.
    //两数之和：先枚举第一个数, 然后找到第二个数
    //三数之和：先枚举第一个数，第二和第三个数用双指针法来降低时间复杂度为O(n)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //先特判
        if (nums == null || nums.length <= 2){
            return res;
        }
        Arrays.sort(nums);
        //i,j,k 对应三个数字的下标
        for (int i = 0; i < nums.length - 2; i++) {
            //去除重复解
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            //每轮更新k的值
            int k = nums.length - 1;
            //双指针法, j从左向右, k从右向左. O(n)
            //分为三数之和< > = 0, <的情况j++, >的情况k--, =的情况找到三元组
            for (int j = i+1; j < k; j++) {
                //去除重复解
                if (j > i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                //先算>0, 则k左移
                while (j < k && (nums[i] + nums[j] + nums[k] > 0)){
                    k--;
                }
                //k左移至j, 则此轮无解
                if (j == k){
                    break;
                }
                //判断是否=0, 不等于则说明<0, 直接j++
                if (nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> tempRes = new ArrayList<>();
                    tempRes.add(nums[i]);
                    tempRes.add(nums[j]);
                    tempRes.add(nums[k]);
                    res.add(tempRes);
                }
            }
        }
        return res;
    }
}
