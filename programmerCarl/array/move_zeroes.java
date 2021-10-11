package programmerCarl.array;

/**
 * @description:
 * @author: Qr
 * @create: 2021-10-11 22:29
 **/
public class move_zeroes {
    //双指针, 分别指向0和非0的元素
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1){
            return;
        }
        int zeroIndex = 0;
        int nonZeroIndex = 0;
        while (zeroIndex < nums.length && nonZeroIndex < nums.length){
            while (zeroIndex < nums.length && nums[zeroIndex] != 0){
                zeroIndex++;
            }
            while (nonZeroIndex < nums.length && nums[nonZeroIndex] == 0){
                nonZeroIndex++;
            }
            //我的方法这里打补丁，就是因为刚开始给zeroIndex赋初始值了
            if (zeroIndex < nonZeroIndex){
                swap(nums,zeroIndex,nonZeroIndex);
                zeroIndex++;
            }
            nonZeroIndex++;
        }
    }

    //自己的方法感觉不够优雅，参考一下官方解法的思想:
    //左指针代表已经处理序列 + 1，即[0,左指针) 为非零数, 也即左指针指向第一个零
    //右指针去遍历,每次右指针遇到非零数, 去swap
    public void moveZeroes_optimized(int[] nums){
        if(nums == null || nums.length == 0 || nums.length == 1){
            return;
        }
        //left不需要去赋初值为第一个零，否则 [1,0]的情况会变成[0,1]
        //那为什么不赋初值也对呢？ left == right; left非0, 相当于就地swap
        //                                   left为0, right变为第一个非0, 跟left swap.
        int left = 0;
        int right = 0;
        while (right < nums.length){
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }



    public void swap(int[] nums, int index1, int index2){
        if (index1 < 0 || index2 < 0 || index1 >= nums.length || index2 >= nums.length){
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = nums[index1];
        return;
    }
}
