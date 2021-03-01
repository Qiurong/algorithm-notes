package binary_search;

/**
 * @description: 153. 寻找旋转排序数组中的最小值
 * @author: Qr
 * @create: 2021-03-01 14:20
 **/
public class find_minimum_in_rotated_sorted_array {
    //从一个完全升序数组变成了两个升序数组组合成一个数组
    //同样用二分的思想去做, 去找到那个小的升序数组, 进入那个升序数组之后也是一样
    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end){
            mid = start + ((end - start) >> 1);
            //小的升序数组在后半部分
            if (nums[mid] > nums[end]){
                start = mid + 1;
            }
            //小的升序数组在前半部分
            else {
                end = mid;
            }
        }
        return nums[start];
    }
}
