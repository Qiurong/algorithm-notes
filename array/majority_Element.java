package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 169. 多数元素
 * @author: Qr
 * @create: 2021-07-21 15:02
 *
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 **/
public class majority_Element {
    //用hashMap来进行count
    public int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int majorNum = 0;
        int n = nums.length;
        int targetCount = n/2 + 1;
        Map<Integer,Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (countMap.containsKey(nums[i])){
                //判断是否达到 目标个数, 需要先put, 再判断
                countMap.put(nums[i], countMap.get(nums[i])+1);
                if (countMap.get(nums[i]) >= targetCount){
                    majorNum = nums[i];
                    break;
                }
            }else {
                countMap.put(nums[i],1);
            }
        }
        return majorNum;
    }

    //摩尔投票法则: 维护两个变量: candidate和count,  如果count=0, 则candidate=遍历数,  candidate==遍历数, 则count++, 否则count--
    //本质就是: 众数和非众数打架, 一对一消掉, 最后众数肯定会多出至少1个
    //但题目只适用于 大于[n/2]的, 如果等于这个方法不适用
    public int majorityElement_vote(int[] nums){
        int candidate = 0;
        int count = 0 ;
        for (int i = 0; i < nums.length; i++) {
            //需要先判断count是否为0
            if (count == 0){
                candidate = nums[i];
            }
            if (candidate == nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return candidate;
    }
}
