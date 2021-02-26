package bitOperation;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @description: 260. 只出现一次的数字 III
 * @author: Qr
 * @create: 2021-02-26 15:29
 **/
public class single_number_iii {

    //用hashSet实现
    public int[] singleNumberWithHash(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])){
                hashSet.remove(nums[i]);
            }else {
                hashSet.add(nums[i]);
            }

        }
        int[] uniqueNums = new int[2];
        int j = 0;
        for(Integer uniqueNum : hashSet){
            uniqueNums[j++] = uniqueNum;
        }
        return uniqueNums;
    }


    //第二种方法：分组异或。我们希望把所有元素分为两组, a和b在不同的的组, 其他元素中相同的元素在同一组, 这样进行分组异或就能得到a和b。
    //1. 异或所有元素得到 x = a ^ b.
    //2. 取x的二进制的最后一个不为0的1的值y作为分组依据,
    //   y的二进制值的1代表 a和b 对应的那一位不同, 那么 a&y 和 b&y 则一个为0一个为1.
    //3. 数组剩余元素中相同元素的 对应那一位肯定相同, 那么相应的 &y 操作之后值也就相同，也就完成了分组操作。
    public int[] singleNumberWithXOR(int[] nums){
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            x = x ^ nums[i];
        }
        //获取x的最后一位1的公式: y = (x&(x-1))^x
        int y = (x&(x-1))^x;
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if((nums[i] & y) == 0){
                firstList.add(nums[i]);
            }else {
                secondList.add(nums[i]);
            }
        }
        int [] uniqueNums = new int[2];
        for (int i = 0; i < firstList.size(); i++) {
            uniqueNums[0] ^= firstList.get(i);
        }
        for (int i = 0; i < secondList.size(); i++) {
            uniqueNums[1] ^= secondList.get(i);
        }
        return uniqueNums;
    }
}
