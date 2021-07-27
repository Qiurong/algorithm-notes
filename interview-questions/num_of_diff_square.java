package interview_questions;

/**
 * @description: 有序数组每个数平方后，不同数字的个数？O(n), 不准使用额外空间
 * @author: Qr
 * @create: 2021-07-27 10:25
 *
 *
 * 给你一个有序整数数组，数组中的数可以是正数、负数、零，请实现一个函数，这个函数返回一个整数：返回这个数组所有数的平方值中有多少种不同的取值。举例：
 *
 * nums = {-1,1,1,1},
 * 那么你应该返回的是：1。因为这个数组所有数的平方取值都是1，只有一种取值
 *
 * nums = {-1,0,1,2,3}
 * 你应该返回4，因为nums数组所有元素的平方值一共4种取值：1,0,4,9
 *
 * (-10，-10，-5，2，8，10)
 * 返回四
 **/
public class num_of_diff_square {
    //双指针从两边向中间靠
    public static int diffSquareNum(int[] nums){
        int n = nums.length;
        //特判
        if (n == 0 || n == 1){
            return n;
        }
        int num = 0;
        int left = 0;
        int right = nums.length-1;
        while (isValidIndex(left,nums) && isValidIndex(right,nums) && left <= right){
            //1.[left]==[right]
            if (Math.abs(nums[left]) == Math.abs(nums[right])){
                num++;
                //左右指针都向中间移至不相等
                int absVal = Math.abs(nums[left]);
                while (left < n && Math.abs(nums[left]) == absVal){
                    left++;
                }
                while (right >= 0 && Math.abs(nums[right]) == absVal){
                    right--;
                }
            }else if (Math.abs(nums[left]) < Math.abs(nums[right])){
                //[left] < [right] 则移动right至下一个 平方不等 的数
                num++;
                int absVal = Math.abs(nums[right]);
                while (isValidIndex(right,nums) && Math.abs(nums[right]) == absVal){
                    right--;
                }
            } else{
                //[left] > [right]
                num++;
                int absVal = Math.abs(nums[left]);
                while (isValidIndex(left,nums) && Math.abs(nums[left]) == absVal){
                    left++;
                }
            }
        }
        return num;
    }

    public static boolean isValidIndex(int index, int[] nums){
        return index >= 0 && index < nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {-5,-3,-1,1,1,2};
        int num = diffSquareNum(nums);
        System.out.println(num);
    }
}
