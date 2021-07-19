package array;

/**
 * @description: 31. 下一个排列
 * @author: Qr
 * @create: 2021-07-16 16:45
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 *
 **/
public class next_permutation {

    /**
     * 希望找到下一个排列，即需要找到最后一个较小的数字，然后把它变大。
     *                 变大的方法是把它之后 大于它的最小的数 跟它交换，同时吧交换后较大数之后的序列排列成升序序列
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1){
            return;
        }
        //1.从后向前找到较小数
        int lastSmallerIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]){
                lastSmallerIndex = i;
                break;
            }
        }
        //该序列为最大序列
        if (lastSmallerIndex == -1){
            reverse(nums,0,nums.length-1);
            return;
        }
        //2.找到较小数之后 最小的大于它的数，即从后向前第一个大于它的数
        int lastBiggerIndex = -1;
        for (int i = nums.length-1; i >= lastSmallerIndex ; i--) {
            if (nums[i] > nums[lastSmallerIndex]){
                lastBiggerIndex = i;
                break;
            }
        }
        //3.交换较小数和较大数
        swap(nums,lastSmallerIndex,lastBiggerIndex);
        //4.吧交换后的较大数之后的序列 从一个降序序列 变成升序序列
        reverse(nums,lastSmallerIndex+1,nums.length-1);
    }

    /**
     * 双指针法吧[start,end]倒序
     * @param nums
     * @param start
     */
    public static void reverse(int[] nums, int start, int end){
        if (start < 0 || end >= nums.length){
            return;
        }
        while (start < end){
            swap(nums,start,end);
            start++;
            end--;
        }
    }

    public static void swap(int[] nums, int i ,int j){
        if (nums [i] == nums[j]){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2};
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

}
