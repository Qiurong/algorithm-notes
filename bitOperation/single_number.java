package bitOperation;

import java.util.HashSet;

/**
 * @description: 136. 只出现一次的数字
 * @author: Qr
 * @create: 2021-02-26 10:53
 **/
public class single_number {

    //用一个HashSet来存储数组中的元素, 如果已经存在则移除这个元素, 最后遍历完了set中只有那个只出现一次的数字
    public int singleNumberWithHash(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        numSet.add(nums[0]);
        for (int i = 1; i < n; i++) {
            if (numSet.contains(nums[i])){
                numSet.remove(nums[i]);
            }else {
                numSet.add(nums[i]);
            }
        }
        //使用迭代器来获取set中唯一的元素
        Integer uniqueNum = numSet.iterator().next();
        return uniqueNum;
    }
}
