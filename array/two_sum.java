package array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 1. 两数之和
 * @author: Qr
 * @create: 2021-04-27 09:03
 **/
public class two_sum {
    //暴搜，时间复杂度O(n^2) 空间O(1)
    //需要遍历所有的x( O(n) ), 其次还需要找到target-x。暴搜的方法就是去找剩余元素, O(n)。所以总的O(n^2).
    public int[] twoSum_brutalForce(int[] nums, int target) {
        int[] res = new int[2];
        //暴搜
        for(int i = 0;i < nums.length;i++){
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    //可以先把所有元素放入hashMap中.这样就能做到寻找 target-x 的操作时间复杂度为O(1).
    //时间复杂度：O(n)   空间复杂度:O(1)
    public int[] twoSum_hashMap(int[] nums, int target){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target-nums[i])){
                //这里需要翻转一下. 第二个数为当前i
                res[1] = i;
                res[0] = hashMap.get(target-nums[i]);
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return res;
    }
}
