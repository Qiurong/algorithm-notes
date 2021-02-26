package bitOperation;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @description: 137. 只出现一次的数字 ||
 * @author: Qr
 * @create: 2021-02-26 14:15
 **/
public class single_number_ii {
    //把数组所有元素加入到hashSet中,最终计算(hashSets所有元素之和 *3 - 数组所有元素的和)/2 = 只出现一次的元素
    //时间复杂度: O(n), 空间复杂度: O(n)
    public int singleNumberWithHash(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int uniqueNum = 0;
        long arrayElementSum = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            arrayElementSum += nums[i];
            hashSet.add(nums[i]);
        }
        long hashSetElementSum = 0;
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            hashSetElementSum += iterator.next();
        }
        uniqueNum = (int) (((hashSetElementSum * 3) - arrayElementSum) / 2);
        return uniqueNum;
    }


}
